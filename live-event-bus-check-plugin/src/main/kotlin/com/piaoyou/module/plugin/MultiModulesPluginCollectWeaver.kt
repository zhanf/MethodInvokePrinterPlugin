package com.piaoyou.module.plugin

import com.quinn.hunter.transform.asm.BaseWeaver
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

/**
 * @Author zhan
 * @Date 3/23/21 8:21 PM
 * @Desc 收集实现了 PluginImpl 的接口
 */
class MultiModulesPluginCollectWeaver(
    private val methodPrinterExtension: MethodPrinterExtension?
) : BaseWeaver() {

    override fun wrapClassWriter(classWriter: ClassWriter?): ClassVisitor {
        return WrapClassVisitor(methodPrinterExtension, classWriter)
    }

    class WrapClassVisitor(
        private val methodPrinterExtension: MethodPrinterExtension?,
        classWriter: ClassWriter?
    ) :
        ClassVisitor(Opcodes.ASM7, classWriter) {

        private var className: String? = null
        override fun visit(
            version: Int,
            access: Int,
            name: String,
            signature: String?,
            superName: String?,
            interfaces: Array<out String>?
        ) {
            this.className = name
            super.visit(version, access, name, signature, superName, interfaces)
        }

        override fun visitMethod(
            access: Int,
            name: String?,
            descriptor: String?,
            signature: String?,
            exceptions: Array<out String>?
        ): MethodVisitor? {
            val vm = super.visitMethod(access, name, descriptor, signature, exceptions)
            if (methodPrinterExtension?.enable != true) {
                return vm
            }
            if (methodPrinterExtension.excludeClassName?.contains(className) == true) {
                return vm
            }
            return Adapter(methodPrinterExtension, className, vm, access, name, descriptor)
//            println("visitMethod after >>> $className  >>> method:$name  >>> descriptor:$descriptor")
//                println("MultiModulesPlugin after >>> $className  >>> method:$name  >>> descriptor:$descriptor")
//            }
//            if (className == "com/jeremyliao/liveeventbus/LiveEventBus\$LiveEvent" && name == "post") {
//                println("MultiModulesPlugin after >>> $className  >>> method:$name  >>> descriptor:$descriptor")
//            return Adapter(className, vm, access, name, descriptor)
        }

    }

    class Adapter(
        private var methodPrinterExtension: MethodPrinterExtension?,
        private var className: String?,
        methodVisitor: MethodVisitor?,
        access: Int,
        name: String?,
        descriptor: String?
    ) : AdviceAdapter(Opcodes.ASM7, methodVisitor, access, name, descriptor) {

        override fun visitMethodInsn(
            opcodeAndSource: Int,
            owner: String?,
            NAME: String?,
            descriptor: String?,
            isInterface: Boolean
        ) {
            methodPrinterExtension?.targetMethod?.forEach {
                it.value.forEach { method ->
                    if (owner == it.key && NAME == method) {
                        println("methodPrinter:className = $className ===>>> invokeMethod = $name ===>>> descriptor = $descriptor ===>>> isInterface:$isInterface")
                    }
                }
            }
//            if (owner == "com/jeremyliao/liveeventbus/LiveEventBus\$Observable" && "post" == NAME) {
//                println("visitMethodInsn after >>> $owner  >>> method:$NAME  >>> descriptor:$descriptor >>> isInterface:$isInterface")
//                println("visitMethodInsn hunt >>> $className >>> method:$name")
//            }
            super.visitMethodInsn(opcodeAndSource, owner, NAME, descriptor, isInterface)
        }

        override fun onMethodEnter() {
            super.onMethodEnter()
//            println("[onMethodEnter]")
//            mv.visitVarInsn(Opcodes.ALOAD, 1);
//            mv.visitMethodInsn(
//                Opcodes.INVOKESTATIC,
//                "com/piaoyou/module/plugin/Hook",
//                "check",
//                "(Ljava/lang/Object;)V",
//                false
//            )
        }
    }
}
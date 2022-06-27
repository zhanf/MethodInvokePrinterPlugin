//package com.piaoyou.module.plugin
//
//import com.quinn.hunter.transform.asm.BaseWeaver
//import org.objectweb.asm.*
//
///**
// * @Author zhan
// * @Date 3/23/21 8:21 PM
// * @Desc map.put(key,value)
// */
//class MultiModulesPluginRegisterWeaver : BaseWeaver() {
//
//    override fun isWeavableClass(fullQualifiedClassName: String): Boolean {
//        return fullQualifiedClassName.startsWith(PLUGIN_IMPL_CLASS_NAME) &&
//                super.isWeavableClass(fullQualifiedClassName)
//    }
//
//    override fun wrapClassWriter(classWriter: ClassWriter?): ClassVisitor {
//        return WrapClassVisitor(classWriter)
//    }
//
//    class WrapClassVisitor(classWriter: ClassWriter?) :
//        ClassVisitor(Opcodes.ASM7, classWriter) {
//
//        override fun visitMethod(
//            access: Int,
//            name: String?,
//            descriptor: String?,
//            signature: String?,
//            exceptions: Array<out String>?
//        ): MethodVisitor {
//            val mv = super.visitMethod(access, name, descriptor, signature, exceptions)
//            return if (name == "<clinit>") {
//                WrapMethodVisitor(mv)
//            } else {
//                mv
//            }
//        }
//    }
//
//    /*
//    mv.visitFieldInsn(GETSTATIC, "com/skiiyis/center/PluginManager", "cache", "Ljava/util/HashMap;");
//    mv.visitLdcInsn(Type.getType("Lcom/skiiyis/center/Plugin;"));
//    mv.visitTypeInsn(NEW, "com/skiiyis/center/PluginManager");
//    mv.visitInsn(DUP);
//    mv.visitMethodInsn(INVOKESPECIAL, "com/skiiyis/center/PluginManager", "<init>", "()V", false);
//    mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashMap", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", false);
//    */
//    class WrapMethodVisitor(
//        mv: MethodVisitor
//    ) : MethodVisitor(Opcodes.ASM7, mv) {
//
//        override fun visitInsn(opcode: Int) {
//            if (opcode == Opcodes.RETURN) {
//                Record.collect.forEach {
//                    mv.visitFieldInsn(
//                        Opcodes.GETSTATIC,
//                        PLUGIN_IMPL_CLASS_NAME_PATH,
//                        ATTR_NAME,
//                        "Ljava/util/HashMap;"
//                    )
//                    mv.visitLdcInsn(Type.getType(it.key))
//                    mv.visitTypeInsn(Opcodes.NEW, it.value)
//                    mv.visitInsn(Opcodes.DUP)
//                    mv.visitMethodInsn(
//                        Opcodes.INVOKESPECIAL,
//                        it.value,
//                        "<init>",
//                        "()V",
//                        false
//                    )
//                    mv.visitMethodInsn(
//                        Opcodes.INVOKEVIRTUAL,
//                        "java/util/HashMap",
//                        "put",
//                        "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
//                        false
//                    )
//                }
//            }
//            super.visitInsn(opcode)
//        }
//    }
//
//
//    companion object {
//        private const val PLUGIN_IMPL_CLASS_NAME = "com.piaoyou.module.plugin_manager.PluginManager"
//        private const val PLUGIN_IMPL_CLASS_NAME_PATH =
//            "com/piaoyou/module/plugin_manager/PluginManager"
//        private const val ATTR_NAME = "cache"
//    }
//}
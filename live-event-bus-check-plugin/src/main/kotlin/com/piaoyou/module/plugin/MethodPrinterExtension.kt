package com.piaoyou.module.plugin

/**
 * @Author zhan
 * @Date 2022/5/15 7:42 下午
 * @Desc
 */
open class MethodPrinterExtension {
    internal var enable = false
    internal var targetMethod: Map<String, List<String>>? = null
    internal var excludeClassName: List<String>? = null

    fun setEnable(enable: Boolean) {
        this.enable = enable
    }

    fun setTargetMethod(targetMethod: Map<String, List<String>>?) {
        this.targetMethod = targetMethod
    }

    fun setExcludeClassName(excludeClassName: List<String>?) {
        this.excludeClassName = excludeClassName
    }
}
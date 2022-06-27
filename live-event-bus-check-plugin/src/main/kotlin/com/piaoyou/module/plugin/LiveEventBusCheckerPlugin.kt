package com.piaoyou.module.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.*

/**
 * @Author zhan
 * @Date 3/22/21 9:10 PM
 * @Desc
 */
class LiveEventBusCheckerPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val methodPrinterExtension =
            project.extensions.create("methodPrinter", MethodPrinterExtension::class.java)
        val appExtension = project.properties["android"] as AppExtension
        appExtension.registerTransform(
            MultiModulesPluginCollectTransform(
                methodPrinterExtension,
                project
            ), Collections.EMPTY_LIST
        )
//        appExtension.registerTransform(MultiModulesPluginRegisterTransform(project))
        project.afterEvaluate {
            println("LiveEventBusCheckerPlugin after >>> apply start")
        }

    }

}
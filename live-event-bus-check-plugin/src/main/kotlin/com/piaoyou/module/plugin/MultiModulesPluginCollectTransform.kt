package com.piaoyou.module.plugin

import com.android.build.api.transform.Context
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import com.quinn.hunter.transform.HunterTransform
import com.quinn.hunter.transform.RunVariant
import org.gradle.api.Project

/**
 * @Author zhan
 * @Date 3/22/21 9:11 PM
 * @Desc
 */
private const val KV_NAME = "live-event-bus-checker"

class MultiModulesPluginCollectTransform(
    private val methodPrinterExtension: MethodPrinterExtension?,
    private val project: Project
) : HunterTransform(project) {

    override fun transform(
        context: Context?,
        inputs: MutableCollection<TransformInput>?,
        referencedInputs: MutableCollection<TransformInput>?,
        outputProvider: TransformOutputProvider?,
        isIncremental: Boolean
    ) {
        this.bytecodeWeaver = MultiModulesPluginCollectWeaver(methodPrinterExtension)
        super.transform(context, inputs, referencedInputs, outputProvider, isIncremental)
    }

    override fun getRunVariant(): RunVariant {
        return super.getRunVariant()
    }

    override fun getName(): String {
        return KV_NAME
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun isIncremental(): Boolean {
        return true
    }

    override fun getScopes(): MutableSet<QualifiedContent.Scope> {
        return mutableSetOf(
            QualifiedContent.Scope.PROJECT,
            QualifiedContent.Scope.SUB_PROJECTS,
            QualifiedContent.Scope.EXTERNAL_LIBRARIES
        )
    }

    override fun getReferencedScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.EMPTY_SCOPES
    }
}
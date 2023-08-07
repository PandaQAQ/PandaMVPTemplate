package com.github.pandaqaq.generator.mvp.activity.simple

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.android.tools.idea.wizard.template.underscoreToCamelCase
import com.github.pandaqaq.generator.mvp.activity.simple.templates.*

/**
 * @author  HuXinYu
 * Desc:    根据模板生成文件
 */
fun RecipeExecutor.simpleActivityRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    prefixName: String,
    activityName: String,
    presenterName: String,
    iViewName: String,
    layoutName: String,
    desc: String,
) {
    val (projectData, srcOut, resOut) = moduleData
    val viewBindingName = "${underscoreToCamelCase(layoutName)}Binding"
    generateManifest(
        moduleData = moduleData,
        activityClass = activityName,
        packageName = packageName.replace(projectData.applicationPackage!!, ""),
        isLauncher = false,
        hasNoActionBar = false,
        isNewModule = false,
        isLibrary = false,
        generateActivityTitle = false
    )
    //生成activity
    save(
        simpleActivityTemp(
            projectData.applicationPackage,
            packageName,
            prefixName,
            activityName,
            presenterName,
            viewBindingName,
            desc
        ), srcOut.resolve("$activityName.kt")
    )
    //生成presenter
    save(
        simplePresenterTemp(projectData.applicationPackage, packageName, prefixName,presenterName, iViewName),
        srcOut.resolve("${presenterName}.kt")
    )
    //生成iView
    save(mvpViewTemp(packageName, iViewName, desc), srcOut.resolve("${iViewName}.kt"))
    //生成layout
    save(mvpLayoutTemp(), resOut.resolve("layout/${layoutName}.xml"))
}
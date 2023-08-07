package com.github.pandaqaq.generator.mvp.fragment.simple

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.android.tools.idea.wizard.template.underscoreToCamelCase
import com.github.pandaqaq.generator.mvp.fragment.simple.templates.*


/**
 * @author  HuXinYu
 * Desc:    生成文件
 */
fun RecipeExecutor.simpleFragmentRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    prefixName: String,
    fragmentName: String,
    presenterName: String,
    iViewName: String,
    layoutName: String,
    desc: String,
) {
    val viewBindingName = "${underscoreToCamelCase(layoutName)}Binding"
    val (projectData, srcOut, resOut) = moduleData
    generateManifest(
        moduleData = moduleData,
        activityClass = fragmentName,
        packageName = packageName.replace(projectData.applicationPackage!!, ""),
        isLauncher = false,
        hasNoActionBar = false,
        isNewModule = false,
        isLibrary = false,
        generateActivityTitle = false
    )
    //生成Fragment
    save(
        mvpFragmentTemp(
            projectData.applicationPackage,
            packageName,
            prefixName,
            fragmentName,
            presenterName,
            viewBindingName,
            desc
        ), srcOut.resolve("$fragmentName.kt")
    )
    //生成presenter
    save(
        simplePresenterTemp(projectData.applicationPackage, packageName, prefixName, presenterName, iViewName),
        srcOut.resolve("${presenterName}.kt")
    )
    //生成iView
    save(mvpViewTemp(packageName, iViewName, desc), srcOut.resolve("${iViewName}.kt"))
    //生成layout
    save(mvpLayoutTemp(), resOut.resolve("layout/${layoutName}.xml"))
}
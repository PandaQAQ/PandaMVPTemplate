package com.github.pandaqaq.generator.mvp.fragment

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.camelCaseToUnderlines
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.android.tools.idea.wizard.template.underscoreToCamelCase
import com.github.pandaqaq.generator.adapter.itemLayoutTemp
import com.github.pandaqaq.generator.adapter.refreshAdapterTemp
import com.github.pandaqaq.generator.emuns.FragmentInitParam
import com.github.pandaqaq.generator.emuns.ItemType
import com.github.pandaqaq.generator.emuns.PageType
import com.github.pandaqaq.generator.mvp.fragment.refresh.refreshFragmentTemp
import com.github.pandaqaq.generator.mvp.fragment.refresh.refreshLayoutTemp
import com.github.pandaqaq.generator.mvp.fragment.refresh.refreshPresenterTemp
import com.github.pandaqaq.generator.mvp.fragment.refresh.refreshViewTemp
import com.github.pandaqaq.generator.mvp.fragment.simple.simpleFragmentTemp

/**
 * @author  HuXinYu
 * Desc:    根据模板生成文件
 */
fun RecipeExecutor.fragmentRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    prefixName: String,
    fragmentName: String,
    presenterName: String,
    iViewName: String,
    layoutName: String,
    pageType: PageType,
    itemType: ItemType,
    initParam: FragmentInitParam
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
    //生成fragment
    val template = if (pageType == PageType.NORMAL) {
        simpleFragmentTemp(
            projectData.applicationPackage ?: "",
            packageName,
            prefixName,
            fragmentName,
            presenterName,
            viewBindingName,
            initParam
        )
    } else {
        refreshFragmentTemp(
            projectData.applicationPackage ?: "",
            packageName,
            prefixName,
            fragmentName,
            presenterName,
            viewBindingName,
            itemType,
            initParam
        )
    }
    save(
        template, srcOut.resolve("$fragmentName.kt")
    )
    //生成presenter
    save(
        refreshPresenterTemp(projectData.applicationPackage, packageName, presenterName, iViewName),
        srcOut.resolve("${presenterName}.kt")
    )
    //生成iView
    save(refreshViewTemp(packageName, iViewName), srcOut.resolve("${iViewName}.kt"))
    //生成layout
    save(refreshLayoutTemp(), resOut.resolve("layout/${layoutName}.xml"))

    //刷新页面还需要生成 Adapter
    if (pageType == PageType.REFRESH && itemType != ItemType.NONE) {
        //生成adapter
        val adapterPackage = "$packageName.adapters"
        val adapterName = "${fragmentName.removeSuffix("Fragment")}Adapter"
        val itemBindingName = "Item${adapterName.replace("Adapter", "Binding")}"
        save(
            refreshAdapterTemp(projectData.applicationPackage, adapterPackage, adapterName, itemBindingName),
            srcOut.resolve("adapters/${adapterName}.kt")
        )
        if (itemType == ItemType.SINGLE) {
            save(
                itemLayoutTemp(),
                resOut.resolve("layout/item_${camelCaseToUnderlines(adapterName.removeSuffix("Adapter"))}.xml")
            )
        }
    }
}
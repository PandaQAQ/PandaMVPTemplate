package com.github.pandaqaq.generator.mvp.fragment

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.github.pandaqaq.generator.mvp.fragment.refresh.refreshFragmentRecipe
import com.github.pandaqaq.generator.mvp.fragment.simple.simpleFragmentRecipe
import com.github.pandaqaq.generator.util.defaultPackageNameParameter

/**
 * @author  HuXinYu
 * Desc:    简单的Fragment生成器
 */
val fragmentGenerator
    get() = template {
        name = "MVP Fragment"
        description = "生成MVP模板代码，包括 Fragment、IView、Presenter、layout"
        minApi = MIN_API

        category = Category.Fragment
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery,
            WizardUiContext.MenuEntry,
        )

        // 组件前缀
        val prefixName = stringParameter {
            name = "Module Prefix"
            default = ""
            help = "请输入组件前缀，build.gradle 中 resourcePrefix 不带下划线部分"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
        }

        // Activity
        val activityName = stringParameter {
            name = "Activity名称"
            help = "Activity名称"
            default = "Activity"
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${toUpperCamelCase(prefixName.value)}Activity" }
        }

        // layout
        val layoutName = stringParameter {
            name = "布局文件名称"
            help = "布局名字，根据类名生成"
            default = ""
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = {
                "${prefixName.value}_activity_${
                    camelCaseToUnderlines(
                        activityName.value.removePrefix(
                            toUpperCamelCase(prefixName.value)
                        ).removeSuffix("Activity")
                    )
                }"
            }
            enabled = { false }
        }

        //IView
        val iViewName = stringParameter {
            name = "IView 文件名称"
            help = "IView接口类，根据类名生成"
            default = ""
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "I${toUpperCamelCase(activityName.value.removeSuffix("Activity"))}View" }
            enabled = { false }
        }

        //presenter
        val presenterName = stringParameter {
            name = "Presenter 文件名称"
            help = "Presenter，根据类名生成"
            default = ""
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${toUpperCamelCase(activityName.value.removeSuffix("Activity"))}Presenter" }
            enabled = { false }
        }

        // 组件前缀
        val isRefreshList = booleanParameter {
            name = "是否为刷新列表页面"
            default = false
            help = "自动生成可刷新的页面，页面默认填充一个带刷新和加载更多的 RecyclerView"
        }

        //desc
        val desc = stringParameter {
            name = "description"
            default = ""
            help = "请输入注释"
            constraints = listOf(Constraint.STRING)
        }

        val packageName = defaultPackageNameParameter

        widgets(
            TextFieldWidget(prefixName),//资源文件限制前缀
            TextFieldWidget(activityName),//Activity名称，layout、iView、presenter 的名称都根据这个来生成
            TextFieldWidget(desc),
            TextFieldWidget(layoutName),
            TextFieldWidget(iViewName),
            TextFieldWidget(presenterName),
            CheckBoxWidget(isRefreshList),
            PackageNameWidget(packageName)
        )

        recipe = {
            if (isRefreshList.value) {
                refreshFragmentRecipe(
                    it as ModuleTemplateData,
                    packageName.value,
                    prefixName.value,
                    activityName.value,
                    presenterName.value,
                    iViewName.value,
                    layoutName.value,
                    desc.value
                )
            } else {
                simpleFragmentRecipe(
                    it as ModuleTemplateData,
                    packageName.value,
                    prefixName.value,
                    activityName.value,
                    presenterName.value,
                    iViewName.value,
                    layoutName.value,
                    desc.value
                )
            }
        }
    }


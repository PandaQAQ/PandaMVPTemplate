package com.github.pandaqaq.generator.mvp.fragment

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.github.pandaqaq.generator.emuns.FragmentInitParam
import com.github.pandaqaq.generator.emuns.ItemType
import com.github.pandaqaq.generator.emuns.PageType
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

        // Fragment
        val fragmentName = stringParameter {
            name = "Fragment 名称"
            help = "Fragment 名称"
            default = "XxFragment"
            constraints = listOf(Constraint.ACTIVITY, Constraint.NONEMPTY,Constraint.UNIQUE)
            suggest = { "${toUpperCamelCase(prefixName.value)}Fragment" }
        }

        // layout
        val layoutName = stringParameter {
            name = "布局文件名称"
            help = "布局名字，根据类名生成"
            default = ""
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = {
                "${prefixName.value}_fragment_${
                    camelCaseToUnderlines(
                        fragmentName.value.removePrefix(
                            toUpperCamelCase(prefixName.value)
                        ).removeSuffix("Fragment")
                    )
                }"
            }
        }

        //IView
        val iViewName = stringParameter {
            name = "IView 文件名称"
            help = "IView接口类，根据类名生成"
            default = ""
            constraints = listOf(Constraint.NONEMPTY,Constraint.CLASS, Constraint.UNIQUE)
            suggest = { "I${toUpperCamelCase(fragmentName.value.removeSuffix("Fragment"))}View" }
        }

        //presenter
        val presenterName = stringParameter {
            name = "Presenter 文件名称"
            help = "Presenter，根据类名生成"
            default = ""
            constraints = listOf(Constraint.NONEMPTY,Constraint.CLASS, Constraint.UNIQUE)
            suggest = { "${toUpperCamelCase(fragmentName.value.removeSuffix("Fragment"))}Presenter" }
        }

        // 是否为刷新页面
        val pageType = enumParameter<PageType> {
            name = "Fragment 为普通Fragment，还是带刷新加载"
            default = PageType.NORMAL
            help = "NORMAL 为普通 Fragment，REFRESH 为自带刷新和加载的列表 Fragment"
        }

        // 列表页是否为多布局 item
        val itemType = enumParameter<ItemType> {
            name = "列表 Fragment Item 类型，创建不同 Adapter 模板"
            default = ItemType.SINGLE
            help = "NONE 不创建Adapter，SINGLE 创建单类型 Adapter，MULTI 创建多类型"
            visible = {
                pageType.value == PageType.REFRESH
            }
        }

        // 基础参数配置
        val initParameter = enumParameter<FragmentInitParam> {
            name = "Fragment 初始化参数"
            help = "FULLSCREEN：需要全屏无Toolbar，WITH_STATE：需要加载状态，BOTH：都需要，NONE：都不需要"
            default = FragmentInitParam.NONE
        }

        val packageName = defaultPackageNameParameter

        widgets(
            TextFieldWidget(prefixName),//资源文件限制前缀
            TextFieldWidget(fragmentName),//Fragment名称，layout、iView、presenter 的名称都根据这个来生成
            TextFieldWidget(layoutName),
            TextFieldWidget(iViewName),
            TextFieldWidget(presenterName),
            EnumWidget(pageType),
            EnumWidget(itemType),
            EnumWidget(initParameter),
            PackageNameWidget(packageName)
        )

        recipe = {
            fragmentRecipe(
                it as ModuleTemplateData,
                packageName.value,
                prefixName.value,
                fragmentName.value,
                presenterName.value,
                iViewName.value,
                layoutName.value,
                pageType.value,
                itemType.value,
                initParameter.value
            )
        }
    }
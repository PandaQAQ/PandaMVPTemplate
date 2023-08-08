package com.github.pandaqaq.generator.mvp.activity

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.github.pandaqaq.generator.emuns.ActivityInitParam
import com.github.pandaqaq.generator.emuns.ItemType
import com.github.pandaqaq.generator.emuns.PageType
import com.github.pandaqaq.generator.util.defaultPackageNameParameter

/**
 * @author  HuXinYu
 * Desc:    简单的Activity生成器
 */
val activityGenerator
    get() = template {
        name = "MVP Activity"
        description = "生成MVP模板代码，包括 Activity、IView、Presenter、layout"
        minApi = MIN_API

        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule,
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
            default = "XxActivity"
            constraints = listOf(Constraint.ACTIVITY, Constraint.NONEMPTY,Constraint.UNIQUE)
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
        }

        //IView
        val iViewName = stringParameter {
            name = "IView 文件名称"
            help = "IView接口类，根据类名生成"
            default = ""
            constraints = listOf(Constraint.NONEMPTY,Constraint.CLASS, Constraint.UNIQUE)
            suggest = { "I${toUpperCamelCase(activityName.value.removeSuffix("Activity"))}View" }
        }

        //presenter
        val presenterName = stringParameter {
            name = "Presenter 文件名称"
            help = "Presenter，根据类名生成"
            default = ""
            constraints = listOf(Constraint.NONEMPTY,Constraint.CLASS, Constraint.UNIQUE)
            suggest = { "${toUpperCamelCase(activityName.value.removeSuffix("Activity"))}Presenter" }
        }

        // 是否为刷新页面
        val pageType = enumParameter<PageType> {
            name = "Activity 为普通Activity，还是带刷新加载"
            default = PageType.NORMAL
            help = "NORMAL 为普通 Activity，REFRESH 为自带刷新和加载的列表 Activity"
        }

        // 列表页是否为多布局 item
        val itemType = enumParameter<ItemType> {
            name = "列表 Activity Item 类型，创建不同 Adapter 模板"
            default = ItemType.SINGLE
            help = "NONE 不创建Adapter，SINGLE 创建单类型 Adapter，MULTI 创建多类型"
            visible = {
                pageType.value == PageType.REFRESH
            }
        }

        // 基础参数配置
        val initParameter = enumParameter<ActivityInitParam> {
            name = "Activity 初始化参数"
            help = "FULLSCREEN：需要全屏无Toolbar，WITH_STATE：需要加载状态，BOTH：都需要，NONE：都不需要"
            default = ActivityInitParam.NONE
        }

        // Activity 标题名称
        val titleName = stringParameter {
            name = "Activity 标题名称"
            default = ""
            help = "请输入Activity标题"
            constraints = listOf(Constraint.NONEMPTY, Constraint.STRING)
            visible = {
                initParameter.value == ActivityInitParam.NONE || initParameter.value == ActivityInitParam.WITH_STATE
            }
        }

        val packageName = defaultPackageNameParameter

        widgets(
            TextFieldWidget(prefixName),//资源文件限制前缀
            TextFieldWidget(activityName),//Activity名称，layout、iView、presenter 的名称都根据这个来生成
            TextFieldWidget(layoutName),
            TextFieldWidget(iViewName),
            TextFieldWidget(presenterName),
            EnumWidget(pageType),
            EnumWidget(itemType),
            EnumWidget(initParameter),
            TextFieldWidget(titleName),
            PackageNameWidget(packageName)
        )

        recipe = {
            activityRecipe(
                it as ModuleTemplateData,
                packageName.value,
                prefixName.value,
                activityName.value,
                presenterName.value,
                iViewName.value,
                layoutName.value,
                pageType.value,
                itemType.value,
                initParameter.value
            )
        }
    }


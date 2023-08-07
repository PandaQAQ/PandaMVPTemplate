package com.github.pandaqaq.generator.mvp.activity.simple.templates

import com.android.tools.idea.wizard.template.toUpperCamelCase
import com.github.pandaqaq.generator.util.time

/**
 * @author  HuXinYu
 * Desc:    mvp presenter 模板
 */
fun simplePresenterTemp(
    applicationPackage: String?,
    packageName: String,
    prefixName:String,
    presenterName: String,
    viewName: String
) = """
    package $packageName

    import $applicationPackage.base.${toUpperCamelCase(prefixName)}BasePresenter

    /**
    * Created by huxinyu on ${time()}.
    * Email : panda.h@foxmail.com
    * Description :
    */
    class $presenterName: ${toUpperCamelCase(prefixName)}BasePresenter<$viewName>() {
    
    }
""".trimIndent()
package com.github.pandaqaq.generator.mvp.activity.simple.templates

import com.android.tools.idea.wizard.template.toUpperCamelCase
import com.github.pandaqaq.generator.util.time


/**
 * @author  HuXinYu
 * Desc:    Mvp Fragment模板
 */
fun simpleActivityTemp(
        applicationPackage: String?,
        packageName: String,
        prefixName:String,
        activityName: String,
        presenterName: String,
        viewBindingName: String,
        desc: String
) = """
package $packageName

import $applicationPackage.base.${toUpperCamelCase(prefixName)}BaseActivity
import $applicationPackage.databinding.$viewBindingName

/**
 * Created by huxinyu on ${time()}.
 * Email : panda.h@foxmail.com
 * Description :$desc
 */
class $activityName: ${toUpperCamelCase(prefixName)}BaseActivity<$presenterName, $viewBindingName>() {
    override fun initVariable() {
        TODO("Not yet implemented")
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun loadData() {
        TODO("Not yet implemented")
    }
}
""".trimIndent()
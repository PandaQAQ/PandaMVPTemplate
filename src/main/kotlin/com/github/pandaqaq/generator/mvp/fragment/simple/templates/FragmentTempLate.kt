package com.github.pandaqaq.generator.mvp.fragment.simple.templates

import com.github.pandaqaq.generator.util.time


/**
 * @author  HuXinYu
 * Desc:    Mvp Fragment模板
 */
fun mvpFragmentTemp(
        applicationPackage: String?,
        packageName: String,
        prefixName:String,
        fragmentName: String,
        presenterName: String,
        viewBindingName: String,
        desc: String
) = """
package $packageName

import $applicationPackage.base.MainBaseFragment
import $applicationPackage.databinding.$viewBindingName

/**
 * Created by huxinyu on ${time()}.
 * Email : panda.h@foxmail.com
 * Description :$desc
 */
class $fragmentName: MainBaseFragment<$presenterName, $viewBindingName>() {
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
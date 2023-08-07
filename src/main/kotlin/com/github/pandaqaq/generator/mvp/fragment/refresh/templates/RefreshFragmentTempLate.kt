package com.github.pandaqaq.generator.mvp.fragment.refresh.templates

import com.android.tools.idea.wizard.template.toUpperCamelCase
import com.github.pandaqaq.generator.util.time


/**
 * @author  HuXinYu
 * Desc:    Mvp Fragment模板
 */
fun refreshFragmentTemp(
        applicationPackage: String?,
        packageName: String,
        prefixName:String,
        fragmentName: String,
        presenterName: String,
        viewBindingName: String,
        desc: String
) = """
package $packageName

import $applicationPackage.base.${toUpperCamelCase(prefixName)}BaseRefreshFragment
import $applicationPackage.databinding.$viewBindingName

/**
 * Created by huxinyu on ${time()}.
 * Email : panda.h@foxmail.com
 * Description :$desc
 */
class $fragmentName: ${toUpperCamelCase(prefixName)}BaseRefreshFragment<$presenterName, $viewBindingName>() {

    override fun bindRefresh(): RefreshRecyclerView {
        return binding.rrvList
    }

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
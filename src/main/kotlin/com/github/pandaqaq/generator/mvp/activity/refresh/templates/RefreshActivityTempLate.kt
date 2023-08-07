package com.github.pandaqaq.generator.mvp.activity.refresh.templates

import com.android.tools.idea.wizard.template.toUpperCamelCase
import com.github.pandaqaq.generator.util.time


/**
 * @author  HuXinYu
 * Desc:    Mvp Fragment模板
 */
fun refreshActivityTemp(
        applicationPackage: String?,
        packageName: String,
        prefixName:String,
        activityName: String,
        presenterName: String,
        viewBindingName: String,
        desc: String
) = """
package $packageName

import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView
import $applicationPackage.base.refresh.${toUpperCamelCase(prefixName)}BaseRefreshActivity
import $applicationPackage.databinding.$viewBindingName

/**
 * Created by huxinyu on ${time()}.
 * Email : panda.h@foxmail.com
 * Description :$desc
 */
class $activityName: MainBaseRefreshActivity<$presenterName, $viewBindingName>() {

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
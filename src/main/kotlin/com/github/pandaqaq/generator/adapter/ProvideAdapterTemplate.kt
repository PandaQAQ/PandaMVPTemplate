package com.github.pandaqaq.generator.adapter

import com.github.pandaqaq.generator.util.time


/**
 * @author  HuXinYu
 * Desc:    mvp view 模板
 */
fun refreshProvideAdapterTemp(
    packageName: String,
    adapterName: String,
) = """
package $packageName

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.module.LoadMoreModule

/**
 * Created by huxinyu on ${time()}.
 * Email : panda.h@foxmail.com
 * Description :
 */
class $adapterName: BaseProviderMultiAdapter<Any>(),LoadMoreModule {
    override fun getItemType(data: List<Any>, position: Int): Int {
        TODO("Not yet implemented")
    }
}
""".trimIndent()
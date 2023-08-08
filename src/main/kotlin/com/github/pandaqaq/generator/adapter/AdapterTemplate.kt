package com.github.pandaqaq.generator.adapter

import com.github.pandaqaq.generator.util.time


/**
 * @author  HuXinYu
 * Desc:    mvp view 模板
 */
fun refreshAdapterTemp(
    applicationPackage: String?,
    packageName: String,
    adapterName: String,
    itemBindingName: String
) = """
package $packageName

import com.pandaq.uires.widget.recyclerview.BindingQuickAdapter
import $applicationPackage.databinding.$itemBindingName

/**
 * Created by huxinyu on ${time()}.
 * Email : panda.h@foxmail.com
 * Description :
 */
class $adapterName: BindingQuickAdapter<Any, $itemBindingName>() {

    override fun convert(holder: BindingHolder<$itemBindingName>, item: Any) {

    }
}
""".trimIndent()
package com.github.pandaqaq.generator.mvp.fragment.refresh

import com.android.tools.idea.wizard.template.toUpperCamelCase
import com.github.pandaqaq.generator.emuns.FragmentInitParam
import com.github.pandaqaq.generator.emuns.ItemType
import com.github.pandaqaq.generator.util.time


/**
 * @author  HuXinYu
 * Desc:    Mvp Fragment模板
 */
fun refreshFragmentTemp(
    applicationPackage: String,
    packageName: String,
    prefixName: String,
    fragmentName: String,
    presenterName: String,
    viewBindingName: String,
    itemType: ItemType,
    initParam: FragmentInitParam

) = """
package $packageName

import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.uires.router.RootRoute
import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandaq.uires.widget.recyclerview.RefreshLoadMoreListener
import $applicationPackage.base.${toUpperCamelCase(prefixName)}BaseRefreshFragment
import $applicationPackage.databinding.$viewBindingName
${getAdapterImport(packageName, fragmentName, itemType)}

/**
 * Created by huxinyu on ${time()}.
 * Email : panda.h@foxmail.com
 * Description :
 */
@Route(path = "${'$'}{RootRoute.${prefixName.uppercase()}}${'$'}{${fragmentName}.ROUTE}")
class $fragmentName: ${toUpperCamelCase(prefixName)}BaseRefreshFragment<$presenterName, $viewBindingName>() {

    companion object {
        const val ROUTE = "${(packageName.removePrefix(applicationPackage)).replace(".", "/")}/$fragmentName"
    }

${initAdapter(fragmentName, itemType)}
       
${initParamCode(initParam)}

    override fun bindRefresh(): RefreshRecyclerView {
        return binding.rrvList
    }

    override fun initVariable() {
        TODO("Not yet implemented")
    }

    override fun initView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rrvList.setLayoutManager(layoutManager)
        binding.rrvList.setAdapter(listAdapter)
        binding.rrvList.setOnRefreshLoadMoreListener(object : RefreshLoadMoreListener {
            override fun onLoadMore() {

            }

            override fun onRefresh() {

            }

        })
        TODO("Not yet implemented")
    }

    override fun loadData() {
        TODO("Not yet implemented")
    }
}
""".trimIndent()

fun getAdapterImport(packageName: String, fragmentName: String, itemType: ItemType): String {
    return when (itemType) {
        ItemType.SINGLE, ItemType.MULTI -> {
            val adapterName = "${fragmentName.removeSuffix("Fragment")}Adapter"
            return "import $packageName.adapters.$adapterName"
        }

        else -> {
            ""
        }
    }
}

fun initAdapter(fragmentName: String, itemType: ItemType): String {
    return when (itemType) {
        ItemType.SINGLE, ItemType.MULTI -> {
            val adapterName = "${fragmentName.removeSuffix("Fragment")}Adapter"
            return "   private val listAdapter by lazy {\n" +
                    "       $adapterName()\n" +
                    "   }"
        }

        else -> {
            ""
        }
    }
}

fun initParamCode(initParam: FragmentInitParam): String {
    return when (initParam) {
        FragmentInitParam.BOTH -> {
            "    override fun isFullScreen(): Boolean = true\n" +
                    "\n" +
                    "    override fun withDefaultState(): Boolean {\n" +
                    "        return true\n" +
                    "    }"
        }

        FragmentInitParam.WITH_STATE -> {
            "    override fun withDefaultState(): Boolean {\n" +
                    "        return true\n" +
                    "    }"
        }

        FragmentInitParam.FULLSCREEN -> {
            "    override fun isFullScreen(): Boolean = true"
        }

        else -> {
            ""
        }
    }
}
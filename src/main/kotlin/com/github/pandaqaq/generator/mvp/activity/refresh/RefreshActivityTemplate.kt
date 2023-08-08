package com.github.pandaqaq.generator.mvp.activity.refresh

import com.android.tools.idea.wizard.template.toUpperCamelCase
import com.github.pandaqaq.generator.emuns.ActivityInitParam
import com.github.pandaqaq.generator.emuns.ItemType
import com.github.pandaqaq.generator.util.time


/**
 * @author  HuXinYu
 * Desc:    Mvp Fragment模板
 */
fun refreshActivityTemp(
    applicationPackage: String,
    packageName: String,
    prefixName: String,
    activityName: String,
    presenterName: String,
    viewBindingName: String,
    itemType: ItemType,
    initParam: ActivityInitParam
) = """
package $packageName

import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.uires.router.RootRoute
import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandaq.uires.widget.recyclerview.RefreshLoadMoreListener
import $applicationPackage.base.refresh.${toUpperCamelCase(prefixName)}BaseRefreshActivity
import $applicationPackage.databinding.$viewBindingName
${getAdapterImport(packageName, activityName, itemType)}

/**
 * Created by huxinyu on ${time()}.
 * Email : panda.h@foxmail.com
 * Description :
 */
@Route(path = "${'$'}{RootRoute.${prefixName.uppercase()}}${'$'}{${activityName}.ROUTE}")
class $activityName: ${toUpperCamelCase(prefixName)}BaseRefreshActivity<$presenterName, $viewBindingName>() {

    companion object {
        const val ROUTE = "${(packageName.removePrefix(applicationPackage)).replace(".", "/")}/$activityName"
    }
    
${initAdapter(activityName, itemType)}
       
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

fun getAdapterImport(packageName: String, activityName: String, itemType: ItemType): String {
    return when (itemType) {
        ItemType.SINGLE, ItemType.MULTI -> {
            val adapterName = "${activityName.removeSuffix("Activity")}Adapter"
            return "import $packageName.adapters.$adapterName"
        }

        else -> {
            ""
        }
    }
}

fun initAdapter(activityName: String, itemType: ItemType): String {
    return when (itemType) {
        ItemType.SINGLE, ItemType.MULTI -> {
            val adapterName = "${activityName.removeSuffix("Activity")}Adapter"
            return "   private val listAdapter by lazy {\n" +
                    "       $adapterName()\n" +
                    "   }"
        }

        else -> {
            ""
        }
    }
}

fun initParamCode(initParam: ActivityInitParam): String {
    return when (initParam) {
        ActivityInitParam.BOTH -> {
                    "    override fun isFullScreen(): Boolean = true\n" +
                    "\n" +
                    "    override fun withDefaultState(): Boolean {\n" +
                    "        return true\n" +
                    "    }"
        }

        ActivityInitParam.WITH_STATE -> {
                    "    override fun withDefaultState(): Boolean {\n" +
                    "        return true\n" +
                    "    }"
        }

        ActivityInitParam.FULLSCREEN -> {
            "    override fun isFullScreen(): Boolean = true"
        }

        else -> {
            ""
        }
    }
}
package com.github.pandaqaq.generator.mvp.activity.simple

import com.android.tools.idea.wizard.template.toUpperCamelCase
import com.github.pandaqaq.generator.emuns.ActivityInitParam
import com.github.pandaqaq.generator.util.time


/**
 * @author  HuXinYu
 * Desc:    Mvp Fragment模板
 */
fun simpleActivityTemp(
    applicationPackage: String,
    packageName: String,
    prefixName: String,
    activityName: String,
    presenterName: String,
    viewBindingName: String,
    initParam: ActivityInitParam
) = """
package $packageName

import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.uires.router.RootRoute
import $applicationPackage.base.${toUpperCamelCase(prefixName)}BaseActivity
import $applicationPackage.databinding.$viewBindingName

/**
 * Created by huxinyu on ${time()}.
 * Email : panda.h@foxmail.com
 * Description :
 */
@Route(path = "${'$'}{RootRoute.${prefixName.uppercase()}}${'$'}{${activityName}.ROUTE}")
class $activityName: ${toUpperCamelCase(prefixName)}BaseActivity<$presenterName, $viewBindingName>() {
   
    companion object {
        const val ROUTE = "${(packageName.removePrefix(applicationPackage)).replace(".", "/")}/$activityName"
    }  
    
${initParamCode(initParam)}
   
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
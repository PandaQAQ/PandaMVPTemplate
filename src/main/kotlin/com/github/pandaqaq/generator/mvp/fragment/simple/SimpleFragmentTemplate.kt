package com.github.pandaqaq.generator.mvp.fragment.simple

import com.android.tools.idea.wizard.template.toUpperCamelCase
import com.github.pandaqaq.generator.emuns.FragmentInitParam
import com.github.pandaqaq.generator.util.time


/**
 * @author  HuXinYu
 * Desc:    Mvp Fragment模板
 */
fun simpleFragmentTemp(
    applicationPackage: String,
    packageName: String,
    prefixName: String,
    fragmentName: String,
    presenterName: String,
    viewBindingName: String,
    initParam: FragmentInitParam

) = """
package $packageName

import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.uires.router.RootRoute
import $applicationPackage.base.${toUpperCamelCase(prefixName)}BaseFragment
import $applicationPackage.databinding.$viewBindingName

/**
 * Created by huxinyu on ${time()}.
 * Email : panda.h@foxmail.com
 * Description :
 */
@Route(path = "${'$'}{RootRoute.${prefixName.uppercase()}}${'$'}{${fragmentName}.ROUTE}")
class $fragmentName: ${toUpperCamelCase(prefixName)}BaseFragment<$presenterName, $viewBindingName>() {

    companion object {
        const val ROUTE = "${(packageName.removePrefix(applicationPackage)).replace(".", "/")}/$fragmentName"
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

fun initParamCode(initParam: FragmentInitParam): String {
    return when (initParam) {
        FragmentInitParam.WITH_STATE -> {
            "    override fun withDefaultState(): Boolean {\n" +
                    "        return true\n" +
                    "    }"
        }

        else -> {
            ""
        }
    }
}
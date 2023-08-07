package com.github.pandaqaq.generator.mvp.fragment.refresh.templates

import com.github.pandaqaq.generator.util.time

/**
 * @author  HuXinYu
 * Desc:    mvp presenter 模板
 */
fun refreshPresenterTemp(
    applicationPackage: String?,
    packageName: String,
    prefixName:String,
    presenterName: String,
    viewName: String
) = """
    package $packageName

    import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView
    import $applicationPackage.base.MainBasePresenter

    /**
    * Created by huxinyu on ${time()}.
    * Email : panda.h@foxmail.com
    * Description :
    */
    class $presenterName: MainBasePresenter<$viewName>() {
    
    }
""".trimIndent()
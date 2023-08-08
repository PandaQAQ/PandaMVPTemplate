package com.github.pandaqaq.generator.mvp.activity.refresh

import com.github.pandaqaq.generator.util.time


/**
 * @author  HuXinYu
 * Desc:    mvp view 模板
 */
fun refreshViewTemp(
        packageName: String,
        viewName: String
) = """
    package $packageName

    import com.pandaq.uires.mvp.core.IView

    /**
     * Created by huxinyu on ${time()}.
     * Email : panda.h@foxmail.com
     * Description :
     */
    interface $viewName : IView {

    }
""".trimIndent()
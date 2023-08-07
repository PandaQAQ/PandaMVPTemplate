package com.github.pandaqaq.generator.mvp.fragment.simple.templates

import com.github.pandaqaq.generator.util.time


/**
 * @author  HuXinYu
 * Desc:    mvp view 模板
 */
fun mvpViewTemp(
        packageName: String,
        viewName: String,
        desc:String
) = """
    package $packageName

    import com.pandaq.uires.mvp.core.IView

    /**
     * Created by huxinyu on ${time()}.
     * Email : panda.h@foxmail.com
     * Description :$desc
     */
    interface $viewName : IView {

    }
""".trimIndent()
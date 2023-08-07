package com.github.pandaqaq.generator.util

import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.stringParameter

/**
 * @author  HuXinYu
 * Desc:    获取包名
 */
val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "com.pandaq.simple"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }

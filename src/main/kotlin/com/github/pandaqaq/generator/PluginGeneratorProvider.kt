package com.github.pandaqaq.generator

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.pandaqaq.generator.mvp.activity.activityGenerator
import com.github.pandaqaq.generator.mvp.fragment.fragmentGenerator

/**
 * @author  HuXinYu
 * Desc:    GeneratorProvider,模板提供给AndroidStudio的入口
 */
class PluginGeneratorProvider : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(
        //Activity
        activityGenerator,
        //Fragment
        fragmentGenerator,
    )
}
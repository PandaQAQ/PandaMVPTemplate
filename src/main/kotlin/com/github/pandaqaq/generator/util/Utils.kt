package com.github.pandaqaq.generator.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * 获取当前时间
 */
fun time(): String {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.CHINA)
    return dateFormat.format(Date())
}
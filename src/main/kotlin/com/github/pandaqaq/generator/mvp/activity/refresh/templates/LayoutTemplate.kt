package com.github.pandaqaq.generator.mvp.activity.refresh.templates

/**
 * @author  HuXinYu
 * Desc:   普通MVP Activity/Fragment 布局文件模板
 */
fun refreshLayoutTemp(
) = """
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <com.pandaq.uires.widget.recyclerview.RefreshRecyclerView
            android:id="@+id/rrv_list"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </androidx.constraintlayout.widget.ConstraintLayout>
""".trimIndent()
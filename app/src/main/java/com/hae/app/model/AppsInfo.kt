package com.hae.app.model

import android.graphics.drawable.Drawable


data class AppsInfo(
    val appName: String,
    val pname: String,
    val versionName: String,
    val versionCode: String,
    val icon: Drawable? = null,
)
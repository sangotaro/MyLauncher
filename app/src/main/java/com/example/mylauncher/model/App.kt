package com.example.mylauncher.model

import android.content.ComponentName
import android.graphics.drawable.Drawable

data class App(
    val icon: Drawable,
    val label: String,
    val componentName: ComponentName
)

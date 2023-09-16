package com.example.mylauncher.ui

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun BackPressedHandler(content: @Composable () -> Unit) {
    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
        }
    }
    LocalOnBackPressedDispatcherOwner.current?.let {
        val dispatcher = it.onBackPressedDispatcher
        DisposableEffect(Unit) {
            dispatcher.addCallback(callback)
            onDispose { callback.remove() }
        }
    }
    content()
}
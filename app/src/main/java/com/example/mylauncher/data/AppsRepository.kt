package com.example.mylauncher.data

import android.content.Context
import com.example.mylauncher.AppLoader
import com.example.mylauncher.model.App
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    suspend fun getAllApps(): List<App> {
        return withContext(Dispatchers.IO) {
            AppLoader.loadAllApps(context)
        }
    }
}
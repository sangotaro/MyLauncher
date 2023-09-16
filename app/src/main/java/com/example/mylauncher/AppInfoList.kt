package com.example.mylauncher

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.ResolveInfoFlags
import android.graphics.drawable.Drawable

object AppInfoList {
    fun create(context: Context): List<AppInfo> {
        val pm = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        return pm.queryIntentActivities(
            intent,
            ResolveInfoFlags.of(PackageManager.MATCH_ALL.toLong())
        )
            .asSequence()
            .mapNotNull { it.activityInfo }
            .filter { it.packageName != context.packageName }
            .map {
                AppInfo(
                    it.loadIcon(pm) ?: getDefaultIcon(context),
                    it.loadLabel(pm).toString(),
                    ComponentName(it.packageName, it.name)
                )
            }
            .sortedBy { it.label }
            .toList()
    }

    fun getDefaultIcon(context: Context): Drawable {
        return context.resources.getDrawable(R.mipmap.ic_launcher_round, null)
    }
}
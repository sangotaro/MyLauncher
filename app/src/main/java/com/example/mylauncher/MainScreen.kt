package com.example.mylauncher

import android.content.ComponentName
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mylauncher.model.App
import com.example.mylauncher.ui.theme.MyLauncherTheme

@Composable
fun MainScreen(viewModel: MainScreenViewModel = viewModel()) {
    AppList(viewModel.uiState.collectAsStateWithLifecycle().value.apps)
}

@Composable
fun AppList(apps: List<App>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        apps.forEach { app ->
            AppItem(app)
        }
    }
}

@Composable
fun AppItem(app: App) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val intent = Intent(Intent.ACTION_MAIN).apply {
                    flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                    addCategory(Intent.CATEGORY_LAUNCHER)
                    component = app.componentName
                }
                context.startActivity(intent)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            bitmap = app.icon.toBitmap().asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(48.dp)
        )
        Text(text = app.label, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun AppItemPreview() {
    val app = App(
        icon = LocalContext.current.getDrawable(R.mipmap.ic_launcher_round)!!,
        label = "Sample App",
        componentName = ComponentName(LocalContext.current, MainActivity::class.java)
    )
    MyLauncherTheme {
        AppItem(app)
    }
}
package com.mediwatch.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import com.mediwatch.core.ui.AndroidTemplateApp
import com.mediwatch.core.ui.AppTheme
import com.mediwatch.core.ui.rememberAppState
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
				AndroidTemplateApp(
					appState = rememberAppState(
						windowSizeClass = calculateWindowSizeClass(
							this
						)
					)
				)
            }
        }
    }
}
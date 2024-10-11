package com.mediwatch.core.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.zIndex
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.mediwatch.core.navigation.AppNavHost
import com.mediwatch.core.navigation.Destination

@OptIn(
	ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class,
	ExperimentalComposeUiApi::class
)
@Composable
fun AndroidTemplateApp(
	appState: AppState
) {
	val snackbarHostState = remember { SnackbarHostState() }
	Scaffold(
		modifier = Modifier.semantics {
			testTagsAsResourceId = true
		},
		containerColor = Color.Transparent,
		contentColor = MaterialTheme.colorScheme.onBackground,
		contentWindowInsets = WindowInsets(0, 0, 0, 0),
		snackbarHost = {
			SnackbarHost(
				snackbarHostState,
				modifier = Modifier
					.systemBarsPadding()
					.navigationBarsPadding()
			)
		},
		
		topBar = {
			val destination = appState.currentDestination
			if (appState.shouldShowTopAppBar) {
				AppTopAppBar(
					modifier = Modifier.zIndex(-1F),
					titleRes = destination?.titleTextId ?: -1,
					actionIcon = AppIcons.Settings,
					actionIconContentDescription = stringResource(
						id = com.mediwatch.R.string.icon
					),
					colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
						containerColor = Color.Transparent
					),
					onActionClick = { },
					navigationIcon = Icons.Default.ArrowBack,
					navigationIconContentDescription = "",
					onNavigationClick = { appState.onBackClick() }
				
				)
			}
		}
	) { padding ->
		AppNavHost(
			navController = appState.navController,
			onBackClick = appState::onBackClick,
			modifier = Modifier
				.padding(padding)
				.consumedWindowInsets(padding)
				.systemBarsPadding()
				.statusBarsPadding()
				.navigationBarsPadding()
		)
	}
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: Destination) =
	this?.hierarchy?.any {
		it.route?.contains(destination.name, true) ?: false
	} ?: false
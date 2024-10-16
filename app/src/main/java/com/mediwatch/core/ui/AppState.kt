package com.mediwatch.core.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mediwatch.core.navigation.Destination
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberAppState(
	windowSizeClass: WindowSizeClass,
	navController: NavHostController = rememberNavController(),
	coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppState {
	return remember(
		navController,
	) {
		AppState(
			navController = navController,
		)
	}
}

@Stable
class AppState(
	val navController: NavHostController,
) {
	val currentDestinationAsState: NavDestination?
		@Composable get() = navController.currentBackStackEntryAsState().value?.destination
	
	val currentDestination: Destination?
		@Composable get() = Destination.values().asList()
			.filter { it.route == currentDestinationAsState?.route }.firstOrNull()
	
	val shouldShowTopAppBar: Boolean
		@Composable get() = Destination.values().asList()
			.filter { it.isTopBarTab }.map { it.route }.contains(currentDestinationAsState?.route)
	
	
	fun onBackClick() {
		navController.popBackStack()
	}
	
}
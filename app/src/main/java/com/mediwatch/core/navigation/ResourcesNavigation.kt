package com.mediwatch.core.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mediwatch.features.resources.ResourceRoute

const val homeGraphRoutePattern = "home_graph"
const val homeNavigationRoute = "home_route"

fun NavController.navigateToHomeGraph(navOptions: NavOptions? = null) {
    this.navigate(homeGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.homeGraph(
    onItemClick: (Int) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
    navController: NavController
) {
    navigation(
        route = homeGraphRoutePattern,
        startDestination = homeNavigationRoute
    ) {
        composable(route = homeNavigationRoute) {
            ResourceRoute(navController = navController, onItemClick = onItemClick)
        }
        nestedGraphs()
    }
}
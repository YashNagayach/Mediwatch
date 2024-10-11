package com.mediwatch.core.navigation

import com.mediwatch.R
import com.mediwatch.core.ui.AppIcons
import com.mediwatch.core.ui.Icon


/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */


enum class Destination(
    val isTopLevelDestination: Boolean,
    val isBottomBarTab: Boolean,
    val isTopBarTab: Boolean,
    val selectedIcon: Icon? = null,
    val unselectedIcon: Icon? = null,
    val iconTextId: Int? = null,
    val titleTextId: Int,
    val route: String
) {
    RESOURCES(
        isTopLevelDestination = true,
        isBottomBarTab = true,
        isTopBarTab = true,
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.Resources),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.ResourcesBorder),
        iconTextId = R.string.resources,
        titleTextId = R.string.resources,
        route = homeNavigationRoute
    ),
    LOGIN(
        isTopLevelDestination = true,
        isBottomBarTab = false,
        isTopBarTab = false,
        titleTextId = R.string.login,
        route = loginNavigationRoute
    ),

    DETAILS(
        isTopLevelDestination = false,
        isBottomBarTab = false,
        isTopBarTab = true,
        titleTextId = R.string.resources,
        route = resourceDetailsNavigationRoute
    )
}
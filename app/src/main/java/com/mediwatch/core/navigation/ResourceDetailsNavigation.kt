package com.mediwatch.core.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mediwatch.core.domain.model.Details
import com.mediwatch.features.resources.DetailsRoute
import org.jetbrains.annotations.VisibleForTesting
import timber.log.Timber

@VisibleForTesting
internal const val resourceIdArg = "resourceId"
internal const val resourceDetailsArg = "resourceDetailsArg"
internal const val resourceDetailsRoute = "resource_details_route"
internal const val resourceDetailsNavigationRoute = "$resourceDetailsRoute/{$resourceIdArg}"

fun NavController.navigateToResourceDetails(resourceId: Int) {
    this.navigate("$resourceDetailsRoute/$resourceId")
}

fun NavGraphBuilder.detailsScreen(
    navController: NavHostController,
    onBackClick: () -> Unit
) {
    composable(
        route = resourceDetailsNavigationRoute,
        arguments = listOf(
            navArgument(resourceIdArg) { type = NavType.StringType },
        )
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val resourceId = arguments.getString(resourceIdArg)
        val resourceDetails =
            navController.previousBackStackEntry?.savedStateHandle?.get<Details>("resourceDetails")
        Timber.tag("resource!!").d(resourceDetails?.name)
        DetailsRoute(resourceId = resourceId, onBackClick = onBackClick)
    }
}
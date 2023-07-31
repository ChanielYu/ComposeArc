package au.auxy.composearc.playground.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import au.auxy.composearc.playground.ui.composable.PlaygroundDetailScreen
import au.auxy.composearc.playground.ui.composable.PlaygroundHomeScreen

fun NavGraphBuilder.playgroundGraph(
    navController: NavController
) {
    navigation(startDestination = "PlaygroundHome", route = PlaygroundNavRoute) {
        composable(route = "PlaygroundHome") {
            PlaygroundHomeScreen(
                hiltViewModel(), { navController.navigateUp() }) {
                navController.navigate("PlaygroundDetail")
            }
        }
        composable(route = "PlaygroundDetail") {
            PlaygroundDetailScreen { navController.navigateUp() }
        }
    }
}

const val PlaygroundNavRoute = "App/Playground"

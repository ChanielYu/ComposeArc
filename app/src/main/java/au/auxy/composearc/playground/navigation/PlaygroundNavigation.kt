package au.auxy.composearc.playground.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import au.auxy.composearc.playground.ui.composable.PlaygroundDetailScreen
import au.auxy.composearc.playground.ui.composable.PlaygroundHomeScreen

fun NavGraphBuilder.playgroundGraph(navController: NavController) {
    navigation(startDestination = "PlaygroundHome", route = PlaygroundNavRoute, arguments = listOf(
        navArgument(PlaygroundParaKey) {
            type = NavType.StringType
            nullable = true
        }
    )) {
        composable(route = "PlaygroundHome") { entry ->
            val para = entry.arguments?.getString(PlaygroundParaKey)
            para?.length
            PlaygroundHomeScreen(
                hiltViewModel(), { navController.navigateUp() }) {
                navController.navigate("PlaygroundDetail")
            }
        }
        composable(route = "PlaygroundDetail") { entry ->
            val para = entry.arguments?.getString(PlaygroundParaKey)
            para?.length
            PlaygroundDetailScreen { navController.navigateUp() }
        }
    }
}

const val PlaygroundParaKey = "playground"
const val PlaygroundNavRoute = "App/Playground?$PlaygroundParaKey={$PlaygroundParaKey}"

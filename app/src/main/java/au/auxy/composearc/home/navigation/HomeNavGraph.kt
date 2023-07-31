package au.auxy.composearc.home.navigation

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import au.auxy.composearc.R
import au.auxy.composearc.home.ui.HomeScreen

fun NavGraphBuilder.homeGraph(
    //navController: NavController
) {
    composable(route = HomeNavRoute) {
        HomeScreen(stringResource(id = R.string.app_home_title))
    }
}

const val HomeNavRoute = "App/Home"

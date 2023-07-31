package au.auxy.composearc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import au.auxy.composearc.home.navigation.HomeNavRoute
import au.auxy.composearc.home.navigation.homeGraph

@Composable
fun MainNavigation(navController: NavHostController) = NavHost(
    navController = navController, startDestination = HomeNavRoute
) {
    homeGraph()
}

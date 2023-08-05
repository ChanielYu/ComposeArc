package au.auxy.composearc.playground.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import au.auxy.composearc.playground.ui.composable.AccountListScreen
import au.auxy.composearc.playground.ui.composable.PlaygroundDetailScreen

internal const val AccountDetailParaKey = "account_detail_para_key"
internal const val AccountNavRoute = "App/Account?$AccountDetailParaKey={$AccountDetailParaKey}"
internal const val AccountListRoute = "App/AccountList"
internal const val AccountDetailRoute = "App/AccountDetail"

internal fun NavGraphBuilder.accountGraph(navController: NavController) {
    navigation(startDestination = AccountListRoute, route = AccountNavRoute) {
        composable(route = AccountListRoute) { entry ->
            val para = entry.arguments?.getString(PlaygroundParaKey)
            para?.length
            AccountListScreen(hiltViewModel(), { navController.navigateUp() }) { //account ->
                navController.navigate(AccountDetailRoute)
            }
        }
        composable(route = AccountDetailRoute, arguments = listOf(
            navArgument(AccountDetailParaKey) {
                type = NavType.StringType
                nullable = true
            }
        )) { entry ->
            val para = entry.arguments?.getString(AccountDetailParaKey)
            para?.length
            PlaygroundDetailScreen { navController.navigateUp() }
        }
    }
}
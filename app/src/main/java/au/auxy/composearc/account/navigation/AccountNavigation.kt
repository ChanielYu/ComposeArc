package au.auxy.composearc.account.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import au.auxy.composearc.account.ui.composable.AccountDetailScreen
import au.auxy.composearc.account.ui.composable.AccountListScreen
import au.auxy.composearc.playground.navigation.PlaygroundParaKey

internal const val AccountDetailNameKey = "account_detail_name_key"
internal const val AccountDetailNumberKey = "account_detail_number_key"
internal const val AccountDetailExtraKey = "account_detail_extra_key"
internal const val AccountNavRoute = "App/Account"
internal const val AccountListRoute = "App/AccountList"
internal const val AccountDetailRoute = "App/AccountDetail?" +
        "$AccountDetailNameKey={$AccountDetailNameKey}&" +
        "$AccountDetailNumberKey={$AccountDetailNumberKey}&" +
        "$AccountDetailExtraKey={$AccountDetailExtraKey}"

internal fun NavGraphBuilder.accountGraph(navController: NavController) {
    navigation(startDestination = AccountListRoute, route = AccountNavRoute) {
        composable(route = AccountListRoute) { entry ->
            val para = entry.arguments?.getString(PlaygroundParaKey)
            para?.length
            AccountListScreen(hiltViewModel(), { navController.navigateUp() }) { account ->
                navController.navigate(account.run {
                    AccountDetailRoute.replace("{$AccountDetailNameKey}", name)
                        .replace("{$AccountDetailNumberKey}", number)
                        .replace("{$AccountDetailExtraKey}", extra)
                })
            }
        }
        composable(route = AccountDetailRoute, arguments = listOf(
            navArgument(AccountDetailNameKey) {
                type = NavType.StringType
                nullable = true
            }, navArgument(AccountDetailNumberKey) {
                type = NavType.StringType
                nullable = true
            }, navArgument(AccountDetailExtraKey) {
                type = NavType.StringType
                nullable = true
            }
        )) {
            AccountDetailScreen(hiltViewModel(), { navController.navigateUp() }) {
                navController.navigateUp()
            }
        }
    }
}

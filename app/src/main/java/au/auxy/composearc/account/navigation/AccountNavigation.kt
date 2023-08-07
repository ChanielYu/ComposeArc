package au.auxy.composearc.account.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import au.auxy.composearc.account.ui.composable.AccountDetailScreen
import au.auxy.composearc.account.ui.composable.AccountEligibilityScreen
import au.auxy.composearc.account.ui.composable.AccountListScreen
import au.auxy.composearc.playground.navigation.PlaygroundParaKey

internal const val AccountNameKey = "account_detail_name_key"
internal const val AccountNumberKey = "account_detail_number_key"
internal const val AccountExtraKey = "account_detail_extra_key"
internal const val AccountNavRoute = "App/Account"
internal const val AccountListRoute = "App/AccountList"
internal const val AccountDetailRoute = "App/AccountDetail?" +
        "$AccountNameKey={$AccountNameKey}&" +
        "$AccountNumberKey={$AccountNumberKey}&" +
        "$AccountExtraKey={$AccountExtraKey}"
internal const val AccountEligibilityRoute = "App/AccountEligibility" +
        "$AccountNameKey={$AccountNameKey}&" +
        "$AccountNumberKey={$AccountNumberKey}&" +
        "$AccountExtraKey={$AccountExtraKey}"

internal fun NavGraphBuilder.accountGraph(navController: NavController) {
    navigation(startDestination = AccountListRoute, route = AccountNavRoute) {
        composable(route = AccountListRoute) { entry ->
            val para = entry.arguments?.getString(PlaygroundParaKey)
            para?.length
            AccountListScreen(hiltViewModel(), { navController.navigateUp() }) { account ->
                navController.navigate(account.run {
                    AccountDetailRoute.replace("{$AccountNameKey}", name)
                        .replace("{$AccountNumberKey}", number)
                        .replace("{$AccountExtraKey}", extra)
                })
            }
        }
        composable(route = AccountDetailRoute, arguments = listOf(
            navArgument(AccountNameKey) {
                type = NavType.StringType
                nullable = true
            }, navArgument(AccountNumberKey) {
                type = NavType.StringType
                nullable = true
            }, navArgument(AccountExtraKey) {
                type = NavType.StringType
                nullable = true
            }
        )) {
            AccountDetailScreen(hiltViewModel(), { navController.navigateUp() }) { account ->
                navController.navigate(account.run {
                    AccountEligibilityRoute.replace("{$AccountNameKey}", name)
                        .replace("{$AccountNumberKey}", number)
                        .replace("{$AccountExtraKey}", extra)
                })
            }
        }
        composable(route = AccountEligibilityRoute, arguments = listOf(
            navArgument(AccountNameKey) {
                type = NavType.StringType
                nullable = true
            }, navArgument(AccountNumberKey) {
                type = NavType.StringType
                nullable = true
            }, navArgument(AccountExtraKey) {
                type = NavType.StringType
                nullable = true
            }
        )) {
            AccountEligibilityScreen(viewModel = hiltViewModel(), { navController.navigateUp() }) {

            }
        }
    }
}

package au.auxy.composearc.playground.ui.fragment

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import au.auxy.composearc.playground.ui.composable.PlaygroundDetailScreen
import au.auxy.composearc.playground.ui.composable.PlaygroundHomeScreen
import au.auxy.composearc.ui.fragment.BaseComposeFragment
import au.auxy.composearc.ui.theme.ComposeArcTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaygroundFragment : BaseComposeFragment() {
    @Composable
    override fun HostScreenContent() = ComposeArcTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "PlaygroundHome") {
            composable(route = "PlaygroundHome") {
                PlaygroundHomeScreen { navController.navigate("PlaygroundDetail") }
            }
            composable(route = "PlaygroundDetail") {
                PlaygroundDetailScreen { navigateBack() }
            }
        }
    }

    private fun navigateBack() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}

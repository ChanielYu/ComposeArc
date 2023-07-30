package au.auxy.composearc.playground.ui.fragment

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import au.auxy.composearc.playground.ui.composable.PlaygroundDetailScreen
import au.auxy.composearc.playground.ui.composable.PlaygroundHomeScreen
import au.auxy.composearc.ui.fragment.BaseComposeFragment
import au.auxy.composearc.ui.theme.ComposeArcThemeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaygroundFragment : BaseComposeFragment() {
    @Composable
    override fun HostScreenContent() = ComposeArcThemeFragment {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "PlaygroundHome") {
            composable(route = "PlaygroundHome") {
                PlaygroundHomeScreen(hiltViewModel()) { navController.navigate("PlaygroundDetail") }
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

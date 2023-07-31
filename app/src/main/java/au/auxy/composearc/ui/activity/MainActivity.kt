package au.auxy.composearc.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import au.auxy.composearc.ui.navigation.MainNavigation
import au.auxy.composearc.ui.theme.ComposeArcTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArcTheme {
                MainNavigation(navController = rememberNavController())
            }
        }
    }
}

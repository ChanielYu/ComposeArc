package au.auxy.composearc.playground.ui.composable

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import au.auxy.composearc.application.AppConst.COMPOSE_TAG
import au.auxy.composearc.ui.theme.ComposeArcTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaygroundHomeScreen(navigateDetail: () -> Unit) {
    Log.d(COMPOSE_TAG, "PlaygroundHomeScreen")
    Surface(
        onClick = navigateDetail,
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.tertiary
    ) {
        Greeting("Android")
    }
}

@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    Log.d(COMPOSE_TAG, "PlaygroundHomeScreen->Greeting")
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    ComposeArcTheme {
        Greeting("Android")
    }
}
package au.auxy.composearc.playground.ui.composable

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import au.auxy.composearc.application.AppConst.COMPOSE_TAG

@Composable
fun PlaygroundDetailScreen(navBack: () -> Unit) {
    Log.d(COMPOSE_TAG, "PlaygroundDetailScreen")
    DetailScreen(navBack = navBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScreen(navBack: () -> Unit) = Scaffold(
    topBar = {
        TopAppBar(
            title = { Text(text = "Detail") },
            navigationIcon = {
                IconButton(onClick = navBack) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack, contentDescription = "Navigate back"
                    )
                }
            })
    }
) { contentPadding ->
    Log.d(COMPOSE_TAG, "PlaygroundDetailScreen->DetailScreen")
    ColumnContent(modifier = Modifier.padding(contentPadding))
}

@Composable
private fun ColumnContent(modifier: Modifier) = LazyColumn(modifier = modifier) {
    Log.d(COMPOSE_TAG, "PlaygroundDetailScreen->DetailScreen->ColumnContent")
    //items()
}

@Preview
@Composable
private fun DetailScreenPreview() {
    DetailScreen {}
}
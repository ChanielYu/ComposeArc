package au.auxy.composearc.playground.ui.composable

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import au.auxy.composearc.application.AppConst.COMPOSE_TAG
import au.auxy.composearc.playground.contract.PlaygroundHomeContract.HomeViewEvent.NavDetail
import au.auxy.composearc.playground.viewmodel.PlaygroundHomeViewModel
import au.auxy.composearc.ui.theme.ComposeArcTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaygroundHomeScreen(viewModel: PlaygroundHomeViewModel, navigateDetail: () -> Unit) {
    Log.d(COMPOSE_TAG, "PlaygroundHomeScreen")
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    SharedScaffold()
    /*Surface(
        onClick = { viewModel.onEvent(NavDetail("")) },
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.tertiary
    ) {
        Greeting("Android", modifier = Modifier
            .wrapContentSize()
            .clickable {
                navigateDetail()
            })
    }*/
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SharedScaffold() {
    //val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Scroll Behavior Test") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            items(50) { item ->
                Text(modifier = Modifier.padding(8.dp), text = "Item $item")
            }
        }
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
private fun GreetingPreview() = ComposeArcTheme {
    SharedScaffold()
    //Greeting("Android")
}
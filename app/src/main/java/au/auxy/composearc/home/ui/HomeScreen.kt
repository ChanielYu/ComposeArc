package au.auxy.composearc.home.ui

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.auxy.composearc.home.model.HomeItem
import au.auxy.composearc.playground.navigation.AccountNavRoute
import au.auxy.composearc.playground.navigation.PlaygroundNavRoute
import au.auxy.composearc.playground.navigation.PlaygroundParaKey
import au.auxy.composearc.ui.theme.ComposeArcTheme

@Composable
fun HomeScreen(title: String, navigate: (route: String) -> Unit) {
    val homeItems = listOf(
        HomeItem("Home", Icons.Default.Home),
        HomeItem("Account", Icons.Default.AccountBox),
        HomeItem("Shopping", Icons.Default.ShoppingCart)
    )
    HomeScreen(title = title, homeItems = homeItems, navigate = navigate)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    title: String,
    homeItems: List<HomeItem>,
    navigate: (route: String) -> Unit
) = Scaffold(
    topBar = { TopAppBar(title = { Text(text = title) }) }
) { paddingValues ->
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = spacedBy(16.dp),
        horizontalArrangement = spacedBy(16.dp)
    ) {
        items(
            count = homeItems.size,
            key = { index ->
                homeItems[index].name
            },
            contentType = { index ->
                homeItems[index]::class
            }) { index ->
            HomeCardItem(
                modifier = Modifier.requiredSize(128.dp),
                name = homeItems[index].name,
                icon = homeItems[index].icon
            ) {
                navigate(
                    when (homeItems[index].name) {
                        "Home" -> PlaygroundNavRoute.replace("{$PlaygroundParaKey}", "ABC")
                        "Account" -> AccountNavRoute
                        "Shopping" -> ""
                        else -> ""
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() = ComposeArcTheme {
    HomeScreen(
        title = "Home screen",
        homeItems = listOf(
            HomeItem("Home", Icons.Default.Home),
            HomeItem("Account", Icons.Default.AccountBox),
            HomeItem("Shopping", Icons.Default.ShoppingCart),
        )
    ) {}
}

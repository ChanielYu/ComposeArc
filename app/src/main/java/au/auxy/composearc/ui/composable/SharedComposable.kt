@file:OptIn(ExperimentalMaterial3Api::class)

package au.auxy.composearc.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.auxy.composearc.ui.theme.ComposeArcTheme

@Composable
internal fun SharedCollapsedScaffold(
    title: String,
    icon: ImageVector = Icons.Default.Menu,
    iconDescription: String = "",
    iconNavigation: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    ),
    content: @Composable (PaddingValues) -> Unit
) = Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
        LargeTopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                IconButton(onClick = iconNavigation) {
                    Icon(imageVector = icon, contentDescription = iconDescription)
                }
            },
            scrollBehavior = scrollBehavior
        )
    },
    content = content
)

@Preview
@Composable
private fun SharedCollapsedScaffoldPreview() = ComposeArcTheme {
    SharedCollapsedScaffold(title = "CollapsedScaffold") { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(50) { item ->
                SharedCardItem(body = "Item $item", modifier = Modifier.fillMaxWidth()) {}
            }
        }
    }
}

@Composable
internal fun SharedToolBarScaffold(
    title: String,
    icon: ImageVector = Icons.Default.ArrowBack,
    iconDescription: String = "",
    iconNavigation: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) = Scaffold(
    topBar = {
        TopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                IconButton(onClick = iconNavigation) {
                    Icon(imageVector = icon, contentDescription = iconDescription)
                }
            }
        )
    },
    content = content
)

@Preview
@Composable
private fun SharedToolBarScaffoldPreview() = ComposeArcTheme {
    SharedToolBarScaffold("ToolBarScaffold") { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(50) { item ->
                SharedCardItem(body = "Item $item", modifier = Modifier.fillMaxWidth()) {}
            }
        }
    }
}

@Composable
internal fun SharedCardItem(
    body: String, modifier: Modifier = Modifier, onClick: (type: String) -> Unit
) = Card(
    onClick = { onClick(body) },
    modifier = modifier,
    elevation = CardDefaults.elevatedCardElevation()
) {
    Text(text = body, modifier = Modifier.padding(16.dp))
}

@Preview
@Composable
private fun SharedCardItemPreview() = ComposeArcTheme {
    SharedCardItem(body = "SharedCardItemPreview", modifier = Modifier) {}
}

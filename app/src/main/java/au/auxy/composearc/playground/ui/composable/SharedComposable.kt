package au.auxy.composearc.playground.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import au.auxy.composearc.repository.Account
import au.auxy.composearc.ui.theme.ComposeArcTheme

@OptIn(ExperimentalMaterial3Api::class)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountCardItem(
    account: Account, modifier: Modifier = Modifier, onClick: (account: Account) -> Unit
) = Card(
    onClick = { onClick(account) },
    modifier = modifier,
    elevation = CardDefaults.elevatedCardElevation()
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        val (name, number, extra) = createRefs()
        Text(text = account.name, modifier = Modifier.constrainAs(name) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(number.top)
            width = Dimension.fillToConstraints
        }, fontSize = 20.sp)
        Text(text = account.number, modifier = Modifier.constrainAs(number) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(name.bottom)
            bottom.linkTo(extra.top)
        })
        Text(text = account.extra, modifier = Modifier.constrainAs(extra) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(number.bottom)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
        }, textAlign = TextAlign.End)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedCardItem(
    body: String, modifier: Modifier = Modifier, onClick: (type: String) -> Unit
) = Card(
    onClick = { onClick(body) },
    modifier = modifier,
    elevation = CardDefaults.elevatedCardElevation()
) {
    Text(text = body, modifier = Modifier.padding(16.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
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

@Preview
@Composable
private fun AccountCardItemPreview() = ComposeArcTheme {
    AccountCardItem(
        account = Account("AccountName", "10086", "88"),
        modifier = Modifier.fillMaxWidth()
    ) {}
}

@Preview
@Composable
private fun SharedCardItemPreview() = ComposeArcTheme {
    SharedCardItem(body = "SharedCardItemPreview", modifier = Modifier) {}
}

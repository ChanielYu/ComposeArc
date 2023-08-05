package au.auxy.composearc.account.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import au.auxy.composearc.account.model.Account
import au.auxy.composearc.ui.theme.ComposeArcTheme

@Composable
internal fun ShimmerContent(modifier: Modifier) = Box(
    modifier = modifier
        .fillMaxSize()
        .background(Color.White)
) {
    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
}

@Preview
@Composable
private fun ShimmerContentPreview() = ComposeArcTheme {
    ShimmerContent(modifier = Modifier.fillMaxSize())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AccountCardItem(
    account: Account, modifier: Modifier, onClick: (account: Account) -> Unit
) = Card(
    onClick = { onClick(account) },
    modifier = modifier,
    elevation = CardDefaults.elevatedCardElevation()
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
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

@Preview
@Composable
private fun AccountCardItemPreview() = ComposeArcTheme {
    AccountCardItem(
        account = Account("AccountName", "10086", "88"),
        modifier = Modifier.fillMaxWidth()
    ) {}
}

@Composable
internal fun AccountSectionItem(
    body: String,
    modifier: Modifier
) = Text(
    text = body, modifier = modifier.fillMaxWidth(), fontSize = 20.sp, fontWeight = FontWeight.Bold
)

@Preview
@Composable
private fun AccountSectionItemPreview() = ComposeArcTheme {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        AccountSectionItem(body = "AccountSectionItem", modifier = Modifier)
    }
}

@Composable
internal fun AccountSimpleItem(
    body: String, modifier: Modifier
) = Text(text = body, modifier = modifier.fillMaxWidth())

@Preview
@Composable
private fun AccountSimpleItemPreview() = ComposeArcTheme {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        AccountSimpleItem(body = "AccountSimpleItem", modifier = Modifier)
    }
}

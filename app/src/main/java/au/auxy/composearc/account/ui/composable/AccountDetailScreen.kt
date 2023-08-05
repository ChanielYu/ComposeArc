package au.auxy.composearc.account.ui.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import au.auxy.composearc.account.contract.AccountContract.AccountDetailContent.Shimmer
import au.auxy.composearc.account.contract.AccountContract.AccountDetailContent.ShowAccountDetail
import au.auxy.composearc.account.contract.AccountContract.AccountDetailContent.ShowError
import au.auxy.composearc.account.model.Account
import au.auxy.composearc.account.ui.model.AccountDetailItem
import au.auxy.composearc.account.ui.model.AccountDetailItem.AccountItem
import au.auxy.composearc.account.ui.model.AccountDetailItem.SectionItem
import au.auxy.composearc.account.ui.model.AccountDetailItem.SimpleItem
import au.auxy.composearc.account.viewmodel.AccountDetailViewModel
import au.auxy.composearc.ui.composable.SharedToolBarScaffold
import au.auxy.composearc.ui.theme.ComposeArcTheme

@Composable
internal fun AccountDetailScreen(
    viewModel: AccountDetailViewModel, navigateUp: () -> Unit, navigate: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    when (val viewContent = viewState.accountDetailContent) {
        Shimmer -> AccountDetailScreen("", navigateUp = navigateUp) { paddingValues ->
            ShimmerContent(modifier = Modifier.padding(paddingValues))
        }

        is ShowAccountDetail -> AccountDetailScreen(
            title = viewContent.title, navigateUp = navigateUp
        ) { paddingValues ->
            AccountListContent(modifier = Modifier.padding(paddingValues), viewContent.details) {}
        }

        ShowError -> TODO()
    }
}

@Composable
private fun AccountDetailScreen(
    title: String, navigateUp: () -> Unit, content: @Composable (PaddingValues) -> Unit
) = SharedToolBarScaffold(title = title, iconNavigation = navigateUp) { paddingValues ->
    content(paddingValues)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AccountListContent(
    modifier: Modifier,
    details: List<AccountDetailItem>,
    navigate: () -> Unit
) = LazyColumn(
    modifier = modifier,
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
) {
    details.forEach { detail ->
        when (detail) {
            is AccountItem -> item(key = detail.uid, contentType = AccountItem::class) {
                AccountCardItem(
                    account = detail.account,
                    modifier = Modifier.animateItemPlacement()
                ) {}
            }

            is SectionItem -> stickyHeader(key = detail.uid, contentType = SectionItem::class) {
                AccountSectionItem(
                    body = detail.sectionText, modifier = Modifier.animateItemPlacement()
                )
            }

            is SimpleItem -> item(key = detail.uid, contentType = SimpleItem::class) {
                AccountSimpleItem(
                    body = detail.body, modifier = Modifier.animateItemPlacement()
                )
            }
        }
    }
}

@Preview
@Composable
private fun AccountDetailScreenPreview() = ComposeArcTheme {
    AccountDetailScreen("ShimmerContentPreview", {}) { paddingValues ->
        AccountListContent(modifier = Modifier.padding(paddingValues), details = buildList {
            add(SectionItem("Account"))
            add(AccountItem(Account("Name", "10086", "88")))
            add(SectionItem("Address"))
            addAll(
                listOf(
                    SimpleItem("8"),
                    SimpleItem("George Street"),
                    SimpleItem("Sydney"),
                    SimpleItem("NSW"),
                    SimpleItem("Australia")
                )
            )
            add(SectionItem("Personal information"))
            addAll(
                listOf(
                    SimpleItem("1998-10-23"),
                    SimpleItem("12345678"),
                    SimpleItem("George@gmail.com")
                )
            )
        }) {}
    }
}

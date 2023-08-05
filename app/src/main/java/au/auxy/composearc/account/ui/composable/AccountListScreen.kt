package au.auxy.composearc.account.ui.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import au.auxy.composearc.account.model.Account
import au.auxy.composearc.account.ui.model.AccountListItem
import au.auxy.composearc.account.ui.model.AccountListItem.AccountItem
import au.auxy.composearc.account.viewmodel.AccountListViewModel
import au.auxy.composearc.application.AppConst.ACCOUNT_LIST_SCREEN_TITLE
import au.auxy.composearc.ui.composable.SharedCollapsedScaffold
import au.auxy.composearc.ui.theme.ComposeArcTheme

@Composable
internal fun AccountListScreen(
    viewModel: AccountListViewModel, navigateUp: () -> Unit, navigate: (account: Account) -> Unit
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    AccountListScreen(accounts = viewState.accounts, navigateUp = navigateUp, navigate = navigate)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun AccountListScreen(
    accounts: List<AccountListItem>, navigateUp: () -> Unit, navigate: (account: Account) -> Unit
) = SharedCollapsedScaffold(
    title = ACCOUNT_LIST_SCREEN_TITLE,
    icon = Icons.Default.ArrowBack,
    iconNavigation = navigateUp
) { paddingValues ->
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        verticalArrangement = spacedBy(8.dp, Alignment.Top)
    ) {
        items(count = accounts.size, key = { idx -> accounts[idx].uid }) { idx ->
            when (val accountItem = accounts[idx]) {
                is AccountItem -> AccountCardItem(
                    account = accountItem.account,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .animateItemPlacement()
                ) {
                    navigate(accountItem.account)
                }
            }
        }
    }
}

@Preview
@Composable
private fun AccountListScreenPreview() = ComposeArcTheme {
    AccountListScreen(List(20) { index ->
        AccountItem(Account("John$index", "10086$index", "88"))
    }, {}) {}
}

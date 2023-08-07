package au.auxy.composearc.account.ui.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import au.auxy.composearc.account.contract.AccountContract
import au.auxy.composearc.account.contract.AccountContract.AccountEligibilityIntent.Navigate
import au.auxy.composearc.account.contract.AccountContract.AccountEligibilityIntent.Refresh
import au.auxy.composearc.account.viewmodel.AccountEligibilityViewModel
import au.auxy.composearc.ui.composable.SharedToolBarScaffold
import au.auxy.composearc.ui.theme.ComposeArcTheme

@Composable
internal fun AccountEligibilityScreen(
    viewModel: AccountEligibilityViewModel, navigateUp: () -> Unit, navigate: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    AccountEligibilityScreen(
        title = viewState.title, navigateUp = navigateUp
    ) { paddingValues ->
        EligibilityContent(
            modifier = Modifier.padding(paddingValues),
            viewState.upStream to viewState.downStream,
            getDestination = { viewModel.onIntent(Refresh) },
            navigate = { viewModel.onIntent(Navigate) })
    }
}

@Composable
private fun AccountEligibilityScreen(
    title: String,
    navigateUp: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) = SharedToolBarScaffold(title = title, iconNavigation = navigateUp) { paddingValues ->
    content(paddingValues)
}

@Composable
private fun EligibilityContent(
    modifier: Modifier,
    streamInfo: Pair<String, String>,
    getDestination: () -> Unit,
    navigate: () -> Unit
) = ConstraintLayout(
    modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
) {
    val (upstream, downstream, check, nav) = createRefs()
    val barrier = createTopBarrier(check, nav)
    Text(text = streamInfo.first, modifier = Modifier.constrainAs(upstream) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
        bottom.linkTo(downstream.top)
    })
    Text(text = streamInfo.second, modifier = Modifier.constrainAs(downstream) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(upstream.bottom)
        bottom.linkTo(barrier)
    })
    OutlinedButton(onClick = getDestination, modifier = Modifier
        .padding(8.dp)
        .constrainAs(check) {
            linkTo(
                start = parent.start, end = nav.start, top = barrier, bottom = parent.bottom
            )
            width = Dimension.fillToConstraints
        }) {
        Text(text = "Check eligibility")
    }
    OutlinedButton(onClick = navigate, modifier = Modifier
        .padding(8.dp)
        .constrainAs(nav) {
            linkTo(
                start = check.end, end = parent.end, top = barrier, bottom = parent.bottom
            )
            width = Dimension.fillToConstraints
        }) {
        Text(text = "Navigate")
    }
}

@Preview
@Composable
private fun AccountEligibilityScreenPreview() = ComposeArcTheme {
    AccountEligibilityScreen(title = "AccountEligibilityScreen", {}) { paddingValues ->
        EligibilityContent(modifier = Modifier.padding(paddingValues),
            "Upstream" to "Downstream", {}) {
        }
    }
}

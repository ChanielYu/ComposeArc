package au.auxy.composearc.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.auxy.composearc.ui.theme.ComposeArcTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeCardItem(
    modifier: Modifier,
    name: String,
    icon: ImageVector,
    iconDescription: String = "",
    onClick: () -> Unit
) = ElevatedCard(
    onClick = onClick, modifier = modifier
) {
    Column {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            modifier = Modifier
                .fillMaxSize()
                .weight(1F),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Preview
@Composable
private fun HomeCardItemPreview() = ComposeArcTheme {
    HomeCardItem(
        modifier = Modifier
            .requiredWidth(128.dp)
            .requiredHeight(128.dp),
        name = "Home",
        icon = Icons.Default.Home
    ) {

    }
}

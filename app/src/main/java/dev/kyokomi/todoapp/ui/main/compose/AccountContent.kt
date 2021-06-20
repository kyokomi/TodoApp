package dev.kyokomi.todoapp.ui.main.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.kyokomi.todoapp.BuildConfig
import dev.kyokomi.todoapp.ui.compose.TodoAppScaffold
import dev.kyokomi.todoapp.ui.license.LicenseActivity
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme

@Composable
fun AccountContent() {
    AccountScreen()
}

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val items = listOf(
        ItemInfo(
            title = "License",
            onClickAction = {
                LicenseActivity.start(context)
            },
        ),
        ItemInfo(
            title = "Version",
            subTitle = "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
        ),
    )
    Column(modifier = modifier) {
        items.forEach {
            AccountListItem(itemInfo = it)
        }
    }
}

private data class ItemInfo(
    val title: String,
    val subTitle: String = "",
    val onClickAction: () -> Unit = {},
)

@Composable
private fun AccountListItem(
    modifier: Modifier = Modifier,
    itemInfo: ItemInfo,
) {
    Box(modifier = modifier.clickable { itemInfo.onClickAction() }) {
        if (itemInfo.subTitle.isEmpty()) {
            OneLineListItem(title = itemInfo.title)
        } else {
            TwoLineListItem(title = itemInfo.title, subTitle = itemInfo.subTitle)
        }
    }
    Divider(modifier = Modifier.height(1.dp))
}

@Composable
private fun OneLineListItem(
    modifier: Modifier = Modifier,
    title: String,
) {
    Box(
        modifier = modifier.height(48.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 12.dp, bottom = 12.dp, end = 16.dp),
        ) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.subtitle1,
            )
        }
    }
}

@Composable
private fun TwoLineListItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
) {
    Box(
        modifier = modifier.height(64.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
        ) {
            Box(
                modifier = modifier
                    .height(28.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
            Box(
                modifier = modifier
                    .height(20.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = subTitle,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAccountScreen() {
    TodoAppTheme {
        TodoAppScaffold(title = "Account") { padding ->
            AccountScreen(modifier = Modifier.padding(padding))
        }
    }
}

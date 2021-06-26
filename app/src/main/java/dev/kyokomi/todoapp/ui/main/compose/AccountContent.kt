package dev.kyokomi.todoapp.ui.main.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.kyokomi.todoapp.BuildConfig
import dev.kyokomi.todoapp.model.AccountSettingEntity
import dev.kyokomi.todoapp.ui.compose.TodoAppScaffold
import dev.kyokomi.todoapp.ui.license.LicenseActivity
import dev.kyokomi.todoapp.ui.main.AccountViewModel
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme

@Composable
fun AccountContent(viewModel: AccountViewModel) {
    val context = LocalContext.current
    val accountSetting by viewModel.accountSetting.collectAsState(initial = AccountSettingEntity())
    AccountScreen(
        onClickLicense = {
            LicenseActivity.start(context)
        },
        accountSetting = accountSetting,
        setAccountSetting = {
            viewModel.editAccountSetting(it)
        },
    )
}

@Composable
fun AccountScreen(
    accountSetting: AccountSettingEntity,
    setAccountSetting: (AccountSettingEntity) -> Unit,
    onClickLicense: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val items = listOf(
        ItemInfo(
            title = "License",
            onClickAction = {
                onClickLicense()
            },
        ),
        ItemInfo(
            title = "Version",
            subTitle = "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
        ),
        ItemInfo(
            title = "Dark Mode",
            content = {
                Switch(
                    checked = accountSetting.darkMode,
                    onCheckedChange = {
                        setAccountSetting(accountSetting.copy(darkMode = it))
                    },
                )
            }
        )
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
    val onClickAction: (() -> Unit)? = null,
    val content: @Composable () -> Unit = {},
)

@Composable
private fun AccountListItem(
    modifier: Modifier = Modifier,
    itemInfo: ItemInfo,
) {
    Box(modifier = itemInfo.onClickAction?.let { modifier.clickable { it() } } ?: modifier) {
        if (itemInfo.subTitle.isEmpty()) {
            OneLineListItem(title = itemInfo.title, content = itemInfo.content)
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
    content: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier.height(48.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 12.dp, bottom = 12.dp, end = 16.dp),
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1.0f),
                style = MaterialTheme.typography.subtitle1,
            )
            content()
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
    val accountSettingEntity = remember { mutableStateOf(AccountSettingEntity()) }

    TodoAppTheme {
        TodoAppScaffold(title = "Account") { padding ->
            AccountScreen(
                modifier = Modifier.padding(padding),
                onClickLicense = {},
                accountSetting = accountSettingEntity.value,
                setAccountSetting = {
                    accountSettingEntity.value = it
                },
            )
        }
    }
}

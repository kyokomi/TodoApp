package dev.kyokomi.todoapp.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun TodoAppScaffold(
    title: String,
    onClickTitle: () -> Unit = {},
    onClickBottomNavigationItem: (item: String) -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    var selectedBottomNavigationItem by remember { mutableStateOf(0) }
    val bottomNavigationItems = listOf("home", "sub", "account")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        modifier = Modifier.clickable {
                            onClickTitle()
                        }
                    )
                },
                actions = actions,
            )
        },
        bottomBar = {
            BottomNavigation {
                bottomNavigationItems.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(item) },
                        selected = selectedBottomNavigationItem == index,
                        onClick = {
                            selectedBottomNavigationItem = index
                            onClickBottomNavigationItem(item)
                        }
                    )
                }
            }
        },
    ) { innerPadding ->
        content(innerPadding)
    }
}
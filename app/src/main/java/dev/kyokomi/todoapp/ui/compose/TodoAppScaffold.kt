package dev.kyokomi.todoapp.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry

data class BottomItem(
    val name: String,
    val icon: ImageVector,
    val actions: @Composable (RowScope.() -> Unit) = {},
    val content: @Composable (NavBackStackEntry) -> Unit,
)

@Composable
fun TodoAppScaffold(
    title: String,
    bottomNavigationItems: List<BottomItem> = listOf(),
    onClickBottomNavigationItem: (item: BottomItem) -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    var selectedBottomNavigationItem by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                actions = if (bottomNavigationItems.isNotEmpty()) {
                    bottomNavigationItems[selectedBottomNavigationItem].actions
                } else {
                    {}
                },
            )
        },
        bottomBar = {
            if (bottomNavigationItems.isNotEmpty()) {
                BottomNavigation {
                    bottomNavigationItems.forEachIndexed { index, item ->
                        BottomNavigationItem(
                            icon = { Icon(item.icon, contentDescription = null) },
                            label = { Text(item.name) },
                            selected = selectedBottomNavigationItem == index,
                            onClick = {
                                selectedBottomNavigationItem = index
                                onClickBottomNavigationItem(item)
                            }
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        content(innerPadding)
    }
}

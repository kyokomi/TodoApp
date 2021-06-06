package dev.kyokomi.todoapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TodoCreateActivityScreen(todoCreateViewModel: TodoCreateViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Create Todo", modifier = Modifier.clickable {
                    })
                },
                actions = {
                    IconButton(onClick = {
                    }) {
                        Icon(Icons.Filled.Settings, contentDescription = null)
                    }
                }
            )
        },
    ) { innerPadding ->
        TodoCreateScreen(
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
fun TodoCreateScreen(
    modifier: Modifier = Modifier,
) {
}

@Preview
@Composable
fun PreviewTodoCreateScreen() {
    val viewModel = TodoCreateViewModel()
    TodoCreateActivityScreen(viewModel)
}
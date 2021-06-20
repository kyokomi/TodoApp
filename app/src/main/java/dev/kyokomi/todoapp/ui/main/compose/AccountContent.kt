package dev.kyokomi.todoapp.ui.main.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.kyokomi.todoapp.ui.compose.TodoAppScaffold
import dev.kyokomi.todoapp.ui.main.MainViewModel
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme

@Composable
fun AccountContent(viewModel: MainViewModel) {
    AccountScreen()
}

@Composable
fun AccountScreen() {
    Box {
        Text("account")
    }
}

@Preview
@Composable
fun PreviewAccountScreen() {
    TodoAppTheme {
        TodoAppScaffold("Account") {
            AccountScreen()
        }
    }
}

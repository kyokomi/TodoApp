package dev.kyokomi.todoapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme

class TodoCreateActivity : ComponentActivity() {
    private val viewModel by viewModels<TodoCreateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                Surface {
                    TodoCreateActivityScreen(viewModel)
                }
            }
        }
    }
}
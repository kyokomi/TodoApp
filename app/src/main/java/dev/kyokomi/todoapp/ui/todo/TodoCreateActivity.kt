package dev.kyokomi.todoapp.ui.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import dagger.hilt.android.AndroidEntryPoint
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme

@AndroidEntryPoint
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
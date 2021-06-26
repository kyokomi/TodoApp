package dev.kyokomi.todoapp.ui.todo.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dev.kyokomi.todoapp.model.AccountSettingEntity
import dev.kyokomi.todoapp.ui.compose.ArrowBackIcon
import dev.kyokomi.todoapp.ui.compose.TodoAppScaffold
import dev.kyokomi.todoapp.ui.extension.finishIfNoTaskStartDefault
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme

@AndroidEntryPoint
class TodoDetailActivity : ComponentActivity() {
    companion object {
        fun start(context: Context, todoId: Long) = context.startActivity(
            Intent(
                context,
                TodoDetailActivity::class.java
            ).apply {
                putExtra("todoId", todoId)
            })
    }

    private val viewModel by viewModels<TodoDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val todoId = intent.getLongExtra("todoId", 0L)
        viewModel.initialize(todoId)

        setContent {
            val accountSettingEntity by viewModel.accountSetting.collectAsState(initial = AccountSettingEntity())
            TodoAppTheme(accountSetting = accountSettingEntity) {
                TodoAppScaffold(
                    title = "TodoDetail",
                    navigationIcon = {
                        ArrowBackIcon(onClick = { finishIfNoTaskStartDefault() })
                    },
                ) { innerPadding ->
                    TodoDetailContent(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}

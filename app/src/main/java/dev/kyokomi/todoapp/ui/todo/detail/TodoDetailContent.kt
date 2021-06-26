package dev.kyokomi.todoapp.ui.todo.detail

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import dev.kyokomi.todoapp.R
import dev.kyokomi.todoapp.model.AccountSettingEntity
import dev.kyokomi.todoapp.ui.compose.TodoAppScaffold
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme

@Composable
fun TodoDetailContent(
    modifier: Modifier = Modifier,
    viewModel: TodoDetailViewModel,
) {
    val todoItemState = viewModel.todoItem.collectAsState(initial = null)
    todoItemState.value?.let { todoItem ->
        TodoDetailScreen(
            title = todoItem.title,
            imagePainter = rememberCoilPainter(
                request = Uri.parse(todoItem.thumbnailUrl),
            ),
            modifier = modifier,
        )
    }
}

@Composable
fun TodoDetailScreen(
    title: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = imagePainter,
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.body1)
        }
    }
}

@Preview
@Composable
fun PreviewTodoCreateScreen() {
    val accountSettingEntity = AccountSettingEntity()
    TodoAppTheme(accountSetting = accountSettingEntity) {
        TodoAppScaffold(
            title = "TodoDetail",
            navigationIcon = {

            },
        ) { innerPadding ->
            TodoDetailScreen(
                modifier = Modifier.padding(innerPadding),
                title = "Test",
                imagePainter = painterResource(R.drawable.ic_noimage),
            )
        }
    }
}

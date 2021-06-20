package dev.kyokomi.todoapp.ui.main.compose

import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import dev.kyokomi.todoapp.model.TodoItem
import dev.kyokomi.todoapp.ui.compose.TodoAppScaffold
import dev.kyokomi.todoapp.ui.main.MainViewModel
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme
import kotlinx.coroutines.delay

@Composable
fun HomeContent(mainViewModel: MainViewModel) {
    val items by mainViewModel.todoItems.collectAsState(listOf())
    HomeScreen(
        items = items,
        onRemoveItem = {},
    )
}

@Composable
fun HomeScreen(
    items: List<TodoItem>,
    onRemoveItem: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isItemLoading by remember { mutableStateOf(false) }

    suspend fun loadItem() {
        if (!isItemLoading) {
            isItemLoading = true
            delay(3000L)
            isItemLoading = false
        }
    }

    LaunchedEffect(Unit) {
        loadItem()
    }

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        items(items = items) { item ->
            PhotographerCard(
                item = item,
                onClickItem = onRemoveItem,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun PhotographerCard(
    item: TodoItem,
    onClickItem: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable { onClickItem(item) },
    ) {
        Image(
            painter = rememberCoilPainter(
                request = item.thumbnailUrl,
            ),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier,
        ) {
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    // https://developer.android.com/reference/android/text/format/DateUtils.html#getRelativeTimeSpanString
                    text = DateUtils.getRelativeTimeSpanString(
                        item.createdAt.toEpochSecond() * 1000,
                        System.currentTimeMillis(),
                        DateUtils.MINUTE_IN_MILLIS,
                        DateUtils.FORMAT_ABBREV_RELATIVE
                            or DateUtils.FORMAT_SHOW_DATE
                            or DateUtils.FORMAT_SHOW_YEAR
                            or DateUtils.FORMAT_ABBREV_MONTH,
                    )
                        .toString(),
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    val items = listOf(
        TodoItem(
            1,
            "Learn compose",
            "https://avatars.githubusercontent.com/u/1456047",
        ),
        TodoItem(
            2,
            "Take the codelab",
            "https://avatars.githubusercontent.com/u/1456047",
        ),
        TodoItem(
            3,
            "Apply state",
            "https://avatars.githubusercontent.com/u/1456047",
        ),
        TodoItem(
            4,
            "Build dynamic UIs",
            "https://avatars.githubusercontent.com/u/1456047",
        ),
    )
    TodoAppTheme {
        TodoAppScaffold(title = "Home") {
            HomeScreen(
                items = items,
                onRemoveItem = {},
            )
        }
    }
}

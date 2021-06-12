package dev.kyokomi.todoapp.ui.main

import android.content.Intent
import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import dev.kyokomi.todoapp.model.TodoItem
import dev.kyokomi.todoapp.ui.todo.TodoCreateActivity
import kotlinx.coroutines.launch

@Composable
fun MainActivityScreen(mainViewModel: MainViewModel) {
    val context = LocalContext.current

    var selectedBottomNavigationItem by remember { mutableStateOf(0) }
    val bottomNavigationItems = listOf("Songs", "Artists", "Playlists")

    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val items: List<TodoItem> by mainViewModel.todoItems.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "TodoApp",
                        modifier = Modifier.clickable {
                            coroutineScope.launch { scrollState.animateScrollToItem(0) }
                        }
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch { scrollState.animateScrollToItem(99) }
                        }
                    ) {
                        Icon(Icons.Filled.Settings, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    context.startActivity(Intent(context, TodoCreateActivity::class.java))
                },
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            BottomNavigation {
                bottomNavigationItems.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(item) },
                        selected = selectedBottomNavigationItem == index,
                        onClick = { selectedBottomNavigationItem = index }
                    )
                }
            }
        },
    ) { innerPadding ->
        MainScreen(
            items = items,
            onRemoveItem = { mainViewModel.removeItem(it) },
//            state = scrollState,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
fun MainScreen(
    items: List<TodoItem>,
    onRemoveItem: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
//        state = state,
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
fun PhotographerCard(
    item: TodoItem,
    onClickItem: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable { onClickItem(item) }
    ) {
        Image(
            painter = rememberCoilPainter(
                request = item.thumbnailUrl,
            ),
            contentDescription = null,
            modifier = Modifier.size(80.dp),
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Text(text = item.title, fontWeight = FontWeight.Bold)
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
fun PreviewTodoScreen() {
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
    MainScreen(
        items = items,
        onRemoveItem = {},
    )
}

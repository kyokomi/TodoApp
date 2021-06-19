package dev.kyokomi.todoapp.ui.main

import android.text.format.DateUtils
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.coil.rememberCoilPainter
import dev.kyokomi.todoapp.model.TodoItem
import dev.kyokomi.todoapp.ui.compose.TodoAppScaffold
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme
import dev.kyokomi.todoapp.ui.todo.TodoCreateActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainActivityScreen(mainViewModel: MainViewModel) {
    val items: List<TodoItem> by mainViewModel.todoItems.collectAsState(initial = emptyList())

    MainScreen(
        items = items,
        onRemoveItem = {
            //mainViewModel.removeItem(it)
        },
    )
}

@Composable
fun MainScreen(
    items: List<TodoItem>,
    onRemoveItem: (TodoItem) -> Unit,
) {
    val context = LocalContext.current
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val navController = rememberNavController()

    TodoAppScaffold(
        title = "TodoApp",
        onClickTitle = {
            coroutineScope.launch { scrollState.animateScrollToItem(0) }
        },
        actions = {
            IconButton(
                onClick = {
                    TodoCreateActivity.start(context)
                },
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        },
        navController = navController,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding),
        ) {
            composable("home") {
                Column {
                    Text("test")
                    HomeScreen(
                        items = items,
                        onRemoveItem = onRemoveItem,
                    )
                }
            }
            composable("sub") { SubScreen() }
            composable("account") { AccountScreen() }
        }
    }
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
            if (isItemLoading) {
                LoadingRow()
            } else {
                PhotographerCard(
                    item = item,
                    onClickItem = onRemoveItem,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun SubScreen() {
    Text("sub")
}

@Composable
fun AccountScreen() {
    Text("account")
}

@Composable
fun PhotographerCard(
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

@Composable
private fun LoadingRow() {
    // Creates an `InfiniteTransition` that runs infinite child animation values.
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        // `infiniteRepeatable` repeats the specified duration-based `AnimationSpec` infinitely.
        animationSpec = infiniteRepeatable(
            // The `keyframes` animates the value by specifying multiple timestamps.
            animation = keyframes {
                // One iteration is 1000 milliseconds.
                durationMillis = 1000
                // 0.7f at the middle of an iteration.
                0.7f at 500
            },
            // When the value finishes animating from 0f to 1f, it repeats by reversing the
            // animation direction.
            repeatMode = RepeatMode.Reverse
        )
    )
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.LightGray.copy(alpha = alpha)),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .background(Color.LightGray.copy(alpha = alpha)),
        )
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
    TodoAppTheme {
        HomeScreen(
            items = items,
            onRemoveItem = {},
        )
    }
}

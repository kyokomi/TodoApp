package dev.kyokomi.todoapp.ui.main.compose

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.kyokomi.todoapp.ui.compose.BottomItem
import dev.kyokomi.todoapp.ui.compose.TodoAppScaffold
import dev.kyokomi.todoapp.ui.main.AccountViewModel
import dev.kyokomi.todoapp.ui.main.MainViewModel
import dev.kyokomi.todoapp.ui.todo.TodoCreateActivity

@Composable
fun MainApp() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val bottomItems = listOf(
        BottomItem(
            name = "Home",
            icon = Icons.Filled.Home,
            content = {
                val viewModel = hiltViewModel<MainViewModel>()
                HomeContent(viewModel)
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
        ),
        BottomItem(
            name = "Account",
            icon = Icons.Filled.AccountBox,
            content = {
                val viewModel = hiltViewModel<AccountViewModel>()
                AccountContent(viewModel)
            },
        ),
    )

    TodoAppScaffold(
        title = "TodoApp",
        onClickBottomNavigationItem = { navItem ->
            navController.navigate(route = navItem.name)
        },
        bottomNavigationItems = bottomItems,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Home",
            modifier = Modifier.padding(innerPadding),
        ) {
            bottomItems.forEach { composable(route = it.name, content = it.content) }
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
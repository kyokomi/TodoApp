package dev.kyokomi.todoapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.kyokomi.todoapp.ui.main.compose.MainApp
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoAppTheme {
                MainApp()
            }
        }
    }
}

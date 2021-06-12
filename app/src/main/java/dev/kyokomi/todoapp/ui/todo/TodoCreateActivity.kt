package dev.kyokomi.todoapp.ui.todo

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material.Surface
import dagger.hilt.android.AndroidEntryPoint
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme

@AndroidEntryPoint
class TodoCreateActivity : ComponentActivity() {
    private val viewModel by viewModels<TodoCreateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val launcher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { fileUri: Uri? ->
                fileUri?.let {
                    // TODO: cropする?

                    // TODO: この画像uriは次回起動時に使えない
                    viewModel.selectImageContent(fileUri)
                }
            }

        setContent {
            TodoAppTheme {
                Surface {
                    TodoCreateActivityScreen(
                        viewModel = viewModel,
                        onOpenImageContent = {
                            launcher.launch("image/*")
                        },
                        onItemComplete = {
                            finish()
                        },
                    )
                }
            }
        }
    }
}
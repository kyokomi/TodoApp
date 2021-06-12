package dev.kyokomi.todoapp.ui.todo

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.core.net.toUri
import dagger.hilt.android.AndroidEntryPoint
import dev.kyokomi.todoapp.common.FileUtil
import dev.kyokomi.todoapp.ui.theme.TodoAppTheme
import java.io.File
import java.io.FileInputStream

@AndroidEntryPoint
class TodoCreateActivity : ComponentActivity() {
    private val viewModel by viewModels<TodoCreateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val launcher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { fileUri: Uri? ->
                fileUri?.let {
                    // TODO: cropする?

                    val filename = FileUtil.getFilename(this, fileUri)
                    val fileInputStream =
                        contentResolver.openInputStream(fileUri) as FileInputStream
                    val file = File(filesDir, filename)
                    file.writeBytes(fileInputStream.readBytes())
                    viewModel.selectImageContent(file.toUri())
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

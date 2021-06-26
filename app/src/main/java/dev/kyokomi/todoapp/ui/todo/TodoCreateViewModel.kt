package dev.kyokomi.todoapp.ui.todo

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kyokomi.todoapp.data.repository.AccountSettingRepository
import dev.kyokomi.todoapp.data.repository.TodoRepository
import dev.kyokomi.todoapp.model.TodoItemEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class TodoCreateViewModel @Inject constructor(
    private val accountSettingRepository: AccountSettingRepository,
    private val todoRepository: TodoRepository,
) : ViewModel() {
    val accountSetting = accountSettingRepository.resolve()

    private var _imageContent = MutableStateFlow<Uri?>(null)
    val imageContent: StateFlow<Uri?> = _imageContent

    fun addItem(text: String) {
        addItem(
            TodoItemEntity(
                id = OffsetDateTime.now().toEpochSecond(),
                title = text,
                thumbnailUrl = imageContent.value?.toString()
                    ?: "https://avatars.githubusercontent.com/u/1456047", // TODO: default image
            )
        )
    }

    private fun addItem(item: TodoItemEntity) = viewModelScope.launch {
        todoRepository.addTodoItem(item)
    }

    fun selectImageContent(contentUri: Uri?) {
        _imageContent.value = contentUri
    }
}

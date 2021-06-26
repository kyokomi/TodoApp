package dev.kyokomi.todoapp.ui.todo.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kyokomi.todoapp.data.repository.AccountSettingRepository
import dev.kyokomi.todoapp.data.repository.TodoRepository
import dev.kyokomi.todoapp.model.TodoItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TodoDetailViewModel @Inject constructor(
    accountSettingRepository: AccountSettingRepository,
    private val todoRepository: TodoRepository,
) : ViewModel() {
    val accountSetting = accountSettingRepository.resolve()
    lateinit var todoItem: Flow<TodoItemEntity>

    fun initialize(id: Long) {
        todoItem = todoRepository.resolve(id)
    }
}

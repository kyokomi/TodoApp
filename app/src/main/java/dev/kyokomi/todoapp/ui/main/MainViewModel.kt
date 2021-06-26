package dev.kyokomi.todoapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kyokomi.todoapp.data.repository.AccountSettingRepository
import dev.kyokomi.todoapp.data.repository.TodoRepository
import dev.kyokomi.todoapp.model.TodoItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accountSettingRepository: AccountSettingRepository,
    private val todoRepository: TodoRepository,
) : ViewModel() {
    val accountSetting = accountSettingRepository.resolve()

    val todoItems: Flow<List<TodoItemEntity>> = todoRepository.resolveAll()

    fun addItem(item: TodoItemEntity) = viewModelScope.launch {
        todoRepository.addTodoItem(item)
    }

    fun removeItem(item: TodoItemEntity) = viewModelScope.launch {
        todoRepository.removeTodoItem(item)
    }
}

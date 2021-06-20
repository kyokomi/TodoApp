package dev.kyokomi.todoapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kyokomi.todoapp.data.repository.TodoRepository
import dev.kyokomi.todoapp.model.TodoItem
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
) : ViewModel() {
    val todoItems: Flow<List<TodoItem>> = todoRepository.resolveAll()

    fun addItem(item: TodoItem) = viewModelScope.launch {
        todoRepository.addTodoItem(item)
    }

    fun removeItem(item: TodoItem) = viewModelScope.launch {
        todoRepository.removeTodoItem(item)
    }
}

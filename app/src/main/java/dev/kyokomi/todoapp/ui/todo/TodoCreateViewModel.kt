package dev.kyokomi.todoapp.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kyokomi.todoapp.data.repository.TodoRepository
import dev.kyokomi.todoapp.model.TodoItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoCreateViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
) : ViewModel() {
    fun addItem(item: TodoItem) = viewModelScope.launch {
        todoRepository.addTodoItem(item)
    }
}
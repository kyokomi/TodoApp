package dev.kyokomi.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.kyokomi.todoapp.model.TodoItem

class MainViewModel : ViewModel() {
    // state: todoItems
    private var _todoItems = MutableLiveData(listOf<TodoItem>())
    val todoItems: LiveData<List<TodoItem>> = _todoItems

    // event: addItem
    fun addItem(item: TodoItem) {
        _todoItems.value = _todoItems.value!! + listOf(item)
    }

    // event: removeItem
    fun removeItem(item: TodoItem) {
        _todoItems.value = _todoItems.value.also { removeItem(item) }
    }
}
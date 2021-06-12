package dev.kyokomi.todoapp.data.repository

import dev.kyokomi.todoapp.data.dao.TodoItemDao
import dev.kyokomi.todoapp.di.DispatcherDefault
import dev.kyokomi.todoapp.di.DispatcherIO
import dev.kyokomi.todoapp.model.TodoItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class TodoRepository @Inject constructor(
    private val todoItemDao: TodoItemDao,
    @DispatcherIO private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    @DispatcherDefault private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    fun resolveAll() = todoItemDao.selectAll()

    suspend fun addTodoItem(todoItem: TodoItem) = withContext(ioDispatcher) {
        todoItemDao.insert(todoItem)
    }

    suspend fun removeTodoItem(todoItem: TodoItem) = withContext(ioDispatcher) {
        todoItemDao.delete(todoItem)
    }
}

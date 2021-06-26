package dev.kyokomi.todoapp.data.repository

import dev.kyokomi.todoapp.data.dao.TodoItemDao
import dev.kyokomi.todoapp.di.DispatcherDefault
import dev.kyokomi.todoapp.di.DispatcherIO
import dev.kyokomi.todoapp.model.TodoItemEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepository @Inject constructor(
    private val todoItemDao: TodoItemDao,
    @DispatcherIO private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    @DispatcherDefault private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    fun resolve(id: Long): Flow<TodoItemEntity> = todoItemDao.selectById(id)
    fun resolveAll(): Flow<List<TodoItemEntity>> = todoItemDao.selectAll()

    suspend fun addTodoItem(todoItem: TodoItemEntity) = withContext(ioDispatcher) {
        todoItemDao.insert(todoItem)
    }

    suspend fun removeTodoItem(todoItem: TodoItemEntity) = withContext(ioDispatcher) {
        todoItemDao.delete(todoItem)
    }
}

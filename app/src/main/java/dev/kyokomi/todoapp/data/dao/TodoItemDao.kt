package dev.kyokomi.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.kyokomi.todoapp.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TodoItemDao {
    @Query("SELECT * FROM todo_item ORDER BY created_at DESC")
    abstract fun selectAll(): Flow<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(vararg todoItem: TodoItem)

    @Delete
    abstract suspend fun delete(todoItem: TodoItem)

    @Query("DELETE FROM todo_item")
    abstract suspend fun deleteAll()
}
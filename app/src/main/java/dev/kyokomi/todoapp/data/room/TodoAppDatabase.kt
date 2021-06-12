package dev.kyokomi.todoapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.kyokomi.todoapp.data.dao.TodoItemDao
import dev.kyokomi.todoapp.model.TodoItem

@Database(entities = [TodoItem::class], version = 1)
@TypeConverters(OffsetDateTimeConverter::class)
abstract class TodoAppDatabase : RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao

    companion object {
        fun buildDatabase(context: Context) = Room
            .databaseBuilder(
                context.applicationContext,
                TodoAppDatabase::class.java, "todo_app.db",
            )
            .fallbackToDestructiveMigration()
            .build()
    }
}

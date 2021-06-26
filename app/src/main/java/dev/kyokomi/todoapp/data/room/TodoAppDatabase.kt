package dev.kyokomi.todoapp.data.room

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.kyokomi.todoapp.data.dao.AccountSettingDao
import dev.kyokomi.todoapp.data.dao.TodoItemDao
import dev.kyokomi.todoapp.model.AccountSettingEntity
import dev.kyokomi.todoapp.model.TodoItemEntity

@Database(
    entities = [
        TodoItemEntity::class,
        AccountSettingEntity::class,
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    exportSchema = true,
)
@TypeConverters(OffsetDateTimeConverter::class)
abstract class TodoAppDatabase : RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao
    abstract fun accountSettingDao(): AccountSettingDao

    companion object {
        fun buildDatabase(context: Context) = Room
            .databaseBuilder(
                context.applicationContext,
                TodoAppDatabase::class.java, "todo_app.db",
            ).fallbackToDestructiveMigration()
            .build()
    }
}

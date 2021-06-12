package dev.kyokomi.todoapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.kyokomi.todoapp.data.room.TodoAppDatabase
import javax.inject.Qualifier
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @DispatcherIO
    @Provides
    fun provideDispatcherIO() = Dispatchers.IO

    @DispatcherDefault
    @Provides
    fun provideDispatcherDefault() = Dispatchers.Default

    @Provides
    fun provideTodoAppDatabase(@ApplicationContext context: Context) =
        TodoAppDatabase.buildDatabase(context)

    @Provides
    fun provideTodoItemDao(todoAppDatabase: TodoAppDatabase) = todoAppDatabase.todoItemDao()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherIO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherDefault

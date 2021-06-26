package dev.kyokomi.todoapp.data.repository

import dev.kyokomi.todoapp.data.dao.AccountSettingDao
import dev.kyokomi.todoapp.di.DispatcherDefault
import dev.kyokomi.todoapp.di.DispatcherIO
import dev.kyokomi.todoapp.model.AccountSettingEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountSettingRepository @Inject constructor(
    private val accountSettingDao: AccountSettingDao,
    @DispatcherIO private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    @DispatcherDefault private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    fun resolve(): Flow<AccountSettingEntity> =
        accountSettingDao.select().map { it ?: AccountSettingEntity() }

    suspend fun update(entity: AccountSettingEntity) = withContext(ioDispatcher) {
        accountSettingDao.insert(entity)
    }
}

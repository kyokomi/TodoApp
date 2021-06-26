package dev.kyokomi.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.kyokomi.todoapp.model.AccountSettingEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AccountSettingDao {
    @Query("SELECT * FROM account_setting WHERE id = :id LIMIT 1")
    abstract fun select(id: Long = 1): Flow<AccountSettingEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: AccountSettingEntity)

    @Update
    abstract suspend fun update(entity: AccountSettingEntity)

    @Delete
    abstract suspend fun delete(entity: AccountSettingEntity)
}
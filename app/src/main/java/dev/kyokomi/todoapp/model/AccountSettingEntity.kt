package dev.kyokomi.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_setting")
data class AccountSettingEntity(
    @PrimaryKey var id: Long = 1,
    @ColumnInfo(name = "dark_mode") val darkMode: Boolean = true,
)

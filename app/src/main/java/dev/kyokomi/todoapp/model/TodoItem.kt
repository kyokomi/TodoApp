package dev.kyokomi.todoapp.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity(tableName = "todo_item")
data class TodoItem(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "thumbnail_url") val thumbnailUrl: String,
    @ColumnInfo(name = "deleted") val deleted: Boolean = false,
    @ColumnInfo(name = "created_at") val createdAt: OffsetDateTime = OffsetDateTime.now(),
    @ColumnInfo(name = "updated_at") val updatedAt: OffsetDateTime = createdAt,
)

enum class TodoIcon(val imageVector: ImageVector, val contentDescription: String) {
    Square(Icons.Default.Menu, "cd_expand"),
    Done(Icons.Default.Done, "cd_done"),
    Event(Icons.Default.Add, "cd_event"),
    Privacy(Icons.Default.Settings, "cd_privacy"),
    Trash(Icons.Default.Favorite, "cd_restore");

    companion object {
        val Default = Square
    }
}

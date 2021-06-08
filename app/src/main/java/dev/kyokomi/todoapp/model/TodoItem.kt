package dev.kyokomi.todoapp.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import java.time.OffsetDateTime

data class TodoItem(
    val id: Long,
    val title: String,
    val thumbnailUrl: String,
    val createdAt: OffsetDateTime,
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
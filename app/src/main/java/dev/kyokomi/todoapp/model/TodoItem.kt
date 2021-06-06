package dev.kyokomi.todoapp.model

import java.time.OffsetDateTime

data class TodoItem(
    val id: Long,
    val title: String,
    val thumbnailUrl: String,
    val createdAt: OffsetDateTime,
)
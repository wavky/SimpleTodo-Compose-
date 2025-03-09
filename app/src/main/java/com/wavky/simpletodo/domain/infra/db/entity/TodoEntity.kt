package com.wavky.simpletodo.domain.infra.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wavky.simpletodo.domain.model.Todo

@Entity(tableName = "todo")
internal data class TodoEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val title: String,
  val description: String,
  val isDone: Boolean,
  val createdAt: Long,
  val doneAt: Long
)

internal fun Todo.convertToEntity(): TodoEntity = TodoEntity(
  id = id,
  title = title,
  description = description,
  isDone = isDone,
  createdAt = createdAt,
  doneAt = doneAt
)

internal fun TodoEntity.convertToModel(): Todo = Todo(
  id = id,
  title = title,
  description = description,
  isDone = isDone,
  createdAt = createdAt,
  doneAt = doneAt
)

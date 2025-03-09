package com.wavky.simpletodo.domain.model

data class Todo(
  val id: Long = 0,
  val title: String,
  val description: String,
  val isDone: Boolean,
  val createdAt: Long,
  val doneAt: Long
)

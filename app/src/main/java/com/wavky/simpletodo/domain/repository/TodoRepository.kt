package com.wavky.simpletodo.domain.repository

import com.wavky.simpletodo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
  fun getAll(): Flow<List<Todo>>
  suspend fun add(todo: Todo): Long
  suspend fun delete(todo: Todo): Int
  suspend fun update(todo: Todo): Int
}

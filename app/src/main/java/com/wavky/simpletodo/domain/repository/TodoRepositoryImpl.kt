package com.wavky.simpletodo.domain.repository

import com.wavky.simpletodo.domain.infra.db.dao.TodoDao
import com.wavky.simpletodo.domain.infra.db.entity.convertToEntity
import com.wavky.simpletodo.domain.infra.db.entity.convertToModel
import com.wavky.simpletodo.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TodoRepositoryImpl(
  private val dao: TodoDao
) : TodoRepository {
  override fun getAll(): Flow<List<Todo>> = dao.getAll().map { list ->
    list.map { it.convertToModel() }
  }

  override suspend fun add(todo: Todo): Long =
    dao.add(todo.convertToEntity())

  override suspend fun delete(todo: Todo): Int =
    dao.delete(todo.convertToEntity())

  override suspend fun update(todo: Todo): Int =
    dao.update(todo.convertToEntity())
}

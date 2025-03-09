package com.wavky.simpletodo.usecase

import com.wavky.simpletodo.domain.model.Todo
import com.wavky.simpletodo.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class UpdateTodoUseCaseImpl(
  private val repository: TodoRepository
) : UpdateTodoUseCase {
  override suspend fun execute(todo: Todo): Boolean =
    withContext(Dispatchers.IO) {
      repository.update(todo) > 0
    }
}

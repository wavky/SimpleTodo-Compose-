package com.wavky.simpletodo.usecase

import com.wavky.simpletodo.domain.model.Todo
import com.wavky.simpletodo.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class DeleteTodoUseCaseImpl(
  private val repository: TodoRepository
) : DeleteTodoUseCase {
  override suspend fun execute(todo: Todo): Boolean =
    withContext(Dispatchers.IO) {
      repository.delete(todo) > 0
    }
}

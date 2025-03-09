package com.wavky.simpletodo.usecase

import com.wavky.simpletodo.domain.model.Todo
import com.wavky.simpletodo.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class AddTodoUseCaseImpl(
  private val repository: TodoRepository
) : AddTodoUseCase {
  override suspend fun execute(title: String, description: String): Boolean =
    withContext(Dispatchers.IO) {
      repository.add(
        Todo(
          title = title,
          description = description,
          isDone = false,
          createdAt = System.currentTimeMillis(),
          doneAt = -1
        )
      ) > 0
    }
}

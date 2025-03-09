package com.wavky.simpletodo.usecase

import com.wavky.simpletodo.domain.model.Todo
import com.wavky.simpletodo.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class GetTodoListUseCaseImpl(
  private val repository: TodoRepository
) : GetTodoListUseCase {
  override fun execute(): Flow<List<Todo>> = repository.getAll()
}

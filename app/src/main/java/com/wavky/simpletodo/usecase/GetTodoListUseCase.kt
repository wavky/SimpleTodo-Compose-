package com.wavky.simpletodo.usecase

import com.wavky.simpletodo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface GetTodoListUseCase {
  fun execute(): Flow<List<Todo>>
}

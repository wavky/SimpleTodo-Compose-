package com.wavky.simpletodo.usecase

import com.wavky.simpletodo.domain.model.Todo

interface UpdateTodoUseCase {
  suspend fun execute(todo: Todo): Boolean
}

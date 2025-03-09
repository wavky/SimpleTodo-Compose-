package com.wavky.simpletodo.usecase

import com.wavky.simpletodo.domain.model.Todo

interface DeleteTodoUseCase {
  suspend fun execute(todo: Todo): Boolean
}

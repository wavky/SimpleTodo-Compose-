package com.wavky.simpletodo.usecase

interface AddTodoUseCase {
  suspend fun execute(title: String, description: String): Boolean
}

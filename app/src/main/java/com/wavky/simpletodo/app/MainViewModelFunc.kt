package com.wavky.simpletodo.app

import com.wavky.simpletodo.domain.model.Todo

interface MainViewModelFunc {
  fun addTodo(title: String, description: String)
  fun updateTodo(todo: Todo)
  fun deleteTodo(todo: Todo)
}

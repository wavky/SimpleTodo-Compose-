package com.wavky.simpletodo.app

import androidx.lifecycle.viewModelScope
import com.wavky.simpletodo.app.ui.BaseViewModel
import com.wavky.simpletodo.domain.model.Todo
import com.wavky.simpletodo.usecase.AddTodoUseCase
import com.wavky.simpletodo.usecase.DeleteTodoUseCase
import com.wavky.simpletodo.usecase.GetTodoListUseCase
import com.wavky.simpletodo.usecase.UpdateTodoUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
  getTodoListUseCase: GetTodoListUseCase,
  private val addTodoUseCase: AddTodoUseCase,
  private val updateTodoUseCase: UpdateTodoUseCase,
  private val deleteTodoUseCase: DeleteTodoUseCase
) : BaseViewModel(), MainViewModelFunc {

  val todoList: StateFlow<List<Todo>> = getTodoListUseCase.execute().stateIn(emptyList())

  override fun addTodo(title: String, description: String) {
    viewModelScope.launch {
      addTodoUseCase.execute(title, description)
    }
  }

  override fun updateTodo(todo: Todo) {
    viewModelScope.launch {
      updateTodoUseCase.execute(todo)
    }
  }

  override fun deleteTodo(todo: Todo) {
    viewModelScope.launch {
      deleteTodoUseCase.execute(todo)
    }
  }
}

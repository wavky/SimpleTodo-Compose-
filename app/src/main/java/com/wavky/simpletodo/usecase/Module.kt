package com.wavky.simpletodo.usecase

import org.koin.dsl.module

val useCaseModule = module {
  factory<GetTodoListUseCase> { GetTodoListUseCaseImpl(get()) }
  factory<AddTodoUseCase> { AddTodoUseCaseImpl(get()) }
  factory<UpdateTodoUseCase> { UpdateTodoUseCaseImpl(get()) }
  factory<DeleteTodoUseCase> { DeleteTodoUseCaseImpl(get()) }
}

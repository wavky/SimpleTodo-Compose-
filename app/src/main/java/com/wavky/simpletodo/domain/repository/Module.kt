package com.wavky.simpletodo.domain.repository

import org.koin.dsl.module

val repositoryModule = module {
  factory<TodoRepository> { TodoRepositoryImpl(get()) }
}

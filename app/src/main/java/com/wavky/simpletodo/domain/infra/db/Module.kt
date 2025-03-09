package com.wavky.simpletodo.domain.infra.db

import androidx.room.Room
import org.koin.dsl.module

val dbModule = module {
  single<AppDatabase> { Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DATABASE_NAME).build() }
  single { get<AppDatabase>().todoDao() }
}

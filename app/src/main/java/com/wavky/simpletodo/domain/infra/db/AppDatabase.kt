package com.wavky.simpletodo.domain.infra.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wavky.simpletodo.domain.infra.db.dao.TodoDao
import com.wavky.simpletodo.domain.infra.db.entity.TodoEntity

//region Database Version
private const val INITIAL = 1
//endregion

@Database(entities = [TodoEntity::class], version = INITIAL)
internal abstract class AppDatabase : RoomDatabase() {
  companion object {
    const val DATABASE_NAME = "simple_todo"
  }

  abstract fun todoDao(): TodoDao
}

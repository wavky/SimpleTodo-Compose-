package com.wavky.simpletodo.domain.infra.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.wavky.simpletodo.domain.infra.db.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TodoDao {

  @Query("SELECT * FROM todo")
  fun getAll(): Flow<List<TodoEntity>>

  @Insert
  suspend fun add(item: TodoEntity): Long

  @Delete
  suspend fun delete(item: TodoEntity): Int

  @Update
  suspend fun update(item: TodoEntity): Int

}

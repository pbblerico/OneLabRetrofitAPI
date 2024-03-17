package com.example.onelabretrofitapi.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CharacterDao {

    @Query("SELECT * FROM $TABLE_NAME_CHARACTER")
    suspend fun getAll(): List<CharacterEntity>

    @Insert
    suspend fun insert(entity: CharacterEntity)

    @Query("DELETE FROM $TABLE_NAME_CHARACTER")
    suspend fun deleteAll()

    @Query("DELETE FROM $TABLE_NAME_CHARACTER WHERE $COLUMN_NAME_CHARACTER_ID = :id")
    suspend fun deleteById(id: Int)
}
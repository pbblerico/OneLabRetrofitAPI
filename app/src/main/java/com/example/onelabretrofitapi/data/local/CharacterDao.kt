package com.example.onelabretrofitapi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.onelabretrofitapi.data.COLUMN_NAME_CHARACTER_ID
import com.example.onelabretrofitapi.data.TABLE_NAME_CHARACTER
import com.example.onelabretrofitapi.data.model.CharacterEntity
import com.example.onelabretrofitapi.presentation.model.Character


@Dao
interface CharacterDao {

    @Query("SELECT * FROM $TABLE_NAME_CHARACTER")
    suspend fun getAll(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CharacterEntity)

    @Insert
    suspend fun insertList(entities: List<CharacterEntity>)

    @Query("DELETE FROM $TABLE_NAME_CHARACTER")
    suspend fun deleteAll()

    @Query("DELETE FROM $TABLE_NAME_CHARACTER WHERE $COLUMN_NAME_CHARACTER_ID = :id")
    suspend fun deleteById(id: Int)

    @Transaction
    suspend fun clearAndInsert(enitites: List<CharacterEntity>) {
        deleteAll()
        insertList(enitites)
    }
}
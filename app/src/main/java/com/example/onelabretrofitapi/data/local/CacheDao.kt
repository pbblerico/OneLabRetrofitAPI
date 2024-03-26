package com.example.onelabretrofitapi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.onelabretrofitapi.data.TABLE_NAME_CACHE
import com.example.onelabretrofitapi.data.TABLE_NAME_CHARACTER
import com.example.onelabretrofitapi.data.model.CharacterCacheEntity
import com.example.onelabretrofitapi.data.model.CharacterEntity


@Dao
interface CacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(entities: List<CharacterCacheEntity>)

    @Query("DELETE FROM $TABLE_NAME_CACHE")
    suspend fun deleteAll()

    @Query("SELECT * FROM $TABLE_NAME_CACHE")
    suspend fun getCache(): List<CharacterCacheEntity>

    @Transaction
    suspend fun clearAndInsert(entities: List<CharacterCacheEntity>) {
        deleteAll()
        insertList(entities)
    }
}
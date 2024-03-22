package com.example.onelabretrofitapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.onelabretrofitapi.data.model.CharacterEntity


@Database(
    entities = [
        CharacterEntity::class
    ],
    version = 2
)
@TypeConverters(
    CustomConverter::class
)
abstract class MyRoomDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharacterDao

}
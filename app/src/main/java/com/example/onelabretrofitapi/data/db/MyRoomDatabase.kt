package com.example.onelabretrofitapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


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
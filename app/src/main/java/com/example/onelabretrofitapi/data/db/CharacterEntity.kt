package com.example.onelabretrofitapi.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = TABLE_NAME_CHARACTER)
data class CharacterEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_NAME_CHARACTER_ID)
    val characterId: Int?,

    @ColumnInfo(name = COLUMN_NAME_CHARACTER_NAME)
    val characterName: String?,

    @ColumnInfo(name = COLUMN_NAME_CHARACTER_STATUS)
    val characterStatus: String?,

    @ColumnInfo(name = COLUMN_NAME_CHARACTER_GENDER)
    val characterGender: String?,

    @ColumnInfo(name = COLUMN_NAME_CHARACTER_SPECIES)
    val characterSpecies: String?,

    @ColumnInfo(name = COLUMN_NAME_CHARACTER_IMAGE)
    val characterImage: String?
)
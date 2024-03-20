package com.example.onelabretrofitapi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.onelabretrofitapi.data.db.COLUMN_NAME_CHARACTER_GENDER
import com.example.onelabretrofitapi.data.db.COLUMN_NAME_CHARACTER_ID
import com.example.onelabretrofitapi.data.db.COLUMN_NAME_CHARACTER_IMAGE
import com.example.onelabretrofitapi.data.db.COLUMN_NAME_CHARACTER_NAME
import com.example.onelabretrofitapi.data.db.COLUMN_NAME_CHARACTER_SPECIES
import com.example.onelabretrofitapi.data.db.COLUMN_NAME_CHARACTER_STATUS
import com.example.onelabretrofitapi.data.db.TABLE_NAME_CHARACTER


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
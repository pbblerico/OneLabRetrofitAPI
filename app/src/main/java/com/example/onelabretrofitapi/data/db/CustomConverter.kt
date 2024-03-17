package com.example.onelabretrofitapi.data.db

import androidx.room.TypeConverter
import com.example.onelabretrofitapi.presentation.model.Character
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class CustomConverter {

    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromCharacterList(characters: List<Character>?): String {
        if (characters == null) return ""
        return json.encodeToString(ListSerializer(Character.serializer()), characters)
    }

    @TypeConverter
    fun toCharacterList(characterString: String?): List<Character> {
        if (characterString.isNullOrEmpty()) return emptyList()
        return json.decodeFromString(ListSerializer(Character.serializer()), characterString)
    }

//    @TypeConverter
//    fun someEnumToString(someStatus: SomeStatus) = someStatus.name
//
//    @TypeConverter
//    fun stringToSomeEnum(someStatusName: String) = SomeStatus.valueOf(someStatusName)
}
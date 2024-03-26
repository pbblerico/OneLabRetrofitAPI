package com.example.onelabretrofitapi.data.repository.datasource.remote

import com.example.onelabretrofitapi.data.api.CharactersApi
import com.example.onelabretrofitapi.data.mapper.toCharacter
import com.example.onelabretrofitapi.data.mapper.toCharacterList
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.model.CharacterList
import javax.inject.Inject

interface RemoteDataSource {
    suspend fun getCharacterList(page: Int = 1): CharacterList

    suspend fun getCharacterById(id: Int): Character
}

class RemoteDataSourceImpl @Inject constructor(
    private val charactersApi: CharactersApi
) : RemoteDataSource {
    override suspend fun getCharacterList(page: Int): CharacterList {
        return charactersApi.getCharacters(page).toCharacterList()
    }

    override suspend fun getCharacterById(id: Int): Character {
        return charactersApi.getCharacterById(id).toCharacter()

    }
}
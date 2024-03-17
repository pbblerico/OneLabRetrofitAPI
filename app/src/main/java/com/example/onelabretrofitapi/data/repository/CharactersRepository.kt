package com.example.onelabretrofitapi.data.repository

import com.example.onelabretrofitapi.core.BaseRepository
import com.example.onelabretrofitapi.core.State
import com.example.onelabretrofitapi.data.api.CharactersApi
import com.example.onelabretrofitapi.data.mapper.toCharacter
import com.example.onelabretrofitapi.data.mapper.toCharacterList
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.model.CharacterList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface CharactersRepository{

    suspend fun getCharacterList(): State<Throwable, CharacterList>

    suspend fun getCharacterById(id: Int): State<Throwable, Character>

}

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi
): CharactersRepository, BaseRepository() {
    override suspend fun getCharacterList(): State<Throwable, CharacterList> = apiCall {
        withContext(Dispatchers.IO) {
            charactersApi.getCharacters().toCharacterList()
        }
    }

    override suspend fun getCharacterById(id: Int): State<Throwable, Character> = apiCall {
        withContext(Dispatchers.IO) {
            charactersApi.getCharacterById(id).toCharacter()
        }
    }

}


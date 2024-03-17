package com.example.onelabretrofitapi.data.repository

import com.example.onelabretrofitapi.core.State
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.model.CharacterList


interface CharactersRepository{

    suspend fun getCharacterList(): State<Throwable, CharacterList>

    suspend fun getCharacterById(id: Int): State<Throwable, Character>

}



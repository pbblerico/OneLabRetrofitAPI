package com.example.onelabretrofitapi.domain.repository

import androidx.paging.PagingData
import com.example.onelabretrofitapi.core.functional.State
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.model.CharacterList
import com.example.onelabretrofitapi.core.functional.Result
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    val observeCharacterStateFlow: Flow<State<List<Character>>>

    suspend fun fetchCharacterList(shouldUpdate: Boolean = false)

    suspend fun getCharacterList(): Result<Throwable, CharacterList>

    suspend fun getCharacterById(id: Int): Result<Throwable, Character>

    suspend fun getAllCharacters(): Result<Throwable, List<Character>>

    suspend fun insertCharacter(character: Character)

    suspend fun deleteById(id: Int)

    suspend fun getCharacterPagingFlow(): Flow<PagingData<Character>>

}

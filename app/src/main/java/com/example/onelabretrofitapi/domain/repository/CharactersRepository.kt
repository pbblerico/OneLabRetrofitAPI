package com.example.onelabretrofitapi.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.onelabretrofitapi.core.BaseRepository
import com.example.onelabretrofitapi.core.functional.State
import com.example.onelabretrofitapi.data.paging.CharacterPagingSource
import com.example.onelabretrofitapi.data.repository.datasource.local.LocalDataSource
import com.example.onelabretrofitapi.data.repository.datasource.remote.RemoteDataSource
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.model.CharacterList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CharactersRepository {

    suspend fun getCharacterList(): State<Throwable, CharacterList>

    suspend fun getCharacterById(id: Int): State<Throwable, Character>

    suspend fun getAllCharacters(): Result<List<Character>>

    suspend fun insertCharacter(character: Character)

    suspend fun deleteById(id: Int)

    suspend fun getCharacterPagingFlow(): Flow<PagingData<Character>>

}

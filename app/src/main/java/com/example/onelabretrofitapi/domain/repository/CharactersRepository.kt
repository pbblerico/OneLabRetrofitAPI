package com.example.onelabretrofitapi.domain.repository

import com.example.onelabretrofitapi.core.BaseRepository
import com.example.onelabretrofitapi.core.State
import com.example.onelabretrofitapi.data.repository.datasource.local.LocalDataSource
import com.example.onelabretrofitapi.data.repository.datasource.remote.RemoteDataSource
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.model.CharacterList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CharactersRepository {

    suspend fun getCharacterList(): State<Throwable, CharacterList>

    suspend fun getCharacterById(id: Int): State<Throwable, Character>

    suspend fun getAllCharacters(): Result<List<Character>>

    suspend fun insertCharacter(character: Character)

    suspend fun deleteById(id: Int)
}

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): CharactersRepository, BaseRepository() {
    override suspend fun getCharacterList(): State<Throwable, CharacterList> = apiCall {
        withContext(Dispatchers.IO) {
            remoteDataSource.getCharacterList()
        }
    }

    override suspend fun getCharacterById(id: Int): State<Throwable, Character> = apiCall {
        withContext(Dispatchers.IO) {
            remoteDataSource.getCharacterById(id)
        }
    }

    override suspend fun getAllCharacters(): Result<List<Character>> {
        return localDataSource.getAllCharacters()
    }

    override suspend fun insertCharacter(character: Character) {
        localDataSource.insertCharacter(character)
    }

    override suspend fun deleteById(id: Int) {
        localDataSource.deleteById(id)
    }

}
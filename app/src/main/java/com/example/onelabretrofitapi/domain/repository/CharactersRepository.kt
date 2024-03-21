package com.example.onelabretrofitapi.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.onelabretrofitapi.core.BaseRepository
import com.example.onelabretrofitapi.core.functional.State
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


//    var characterFlow: Flow<PagingData<Character>>
}

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val pagingSource: CharacterPagingSource
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

    override suspend fun getCharacterPagingFlow(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false, initialLoadSize = 1),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

//    override var characterFlow: Flow<PagingData<Character>> = Pager(
//        config = PagingConfig(20, enablePlaceholders = false, initialLoadSize = 1),
//        pagingSourceFactory = { pagingSource }
//    ).flow
}
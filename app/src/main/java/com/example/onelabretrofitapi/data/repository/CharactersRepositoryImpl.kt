package com.example.onelabretrofitapi.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.onelabretrofitapi.core.BaseRepository
import com.example.onelabretrofitapi.core.NetworkChecker
import com.example.onelabretrofitapi.core.functional.Result
import com.example.onelabretrofitapi.core.functional.State
import com.example.onelabretrofitapi.data.paging.CharacterPagingSource
import com.example.onelabretrofitapi.data.repository.datasource.local.LocalDataSource
import com.example.onelabretrofitapi.data.repository.datasource.remote.RemoteDataSource
import com.example.onelabretrofitapi.domain.repository.CharactersRepository
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.model.CharacterList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random


class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val pagingSource: CharacterPagingSource,
    private val networkChecker: NetworkChecker
) : CharactersRepository, BaseRepository() {

    private val _characterDataFlow = MutableStateFlow<State<List<Character>>>(State.Initial)
    override val observeCharacterStateFlow: Flow<State<List<Character>>>
        get() = _characterDataFlow

    override suspend fun fetchCharacterList(shouldUpdate: Boolean) =
        withContext(Dispatchers.IO) {
            try {
                val cachedCharacters = localDataSource.getAllCharacters() as Result.Success
                if (cachedCharacters.b.isEmpty()) {
                    _characterDataFlow.emit(State.Loading)
                } else {
                    Log.d("Characters Repo", "Cache")
                    _characterDataFlow.emit(State.Data(cachedCharacters.b))
                }
                if (networkChecker.isConnected) {
                    Log.d("Characters Repo", "Internet is connected")
                    val randomPage = Random.Default.nextInt(1, 40)
                    val characters = remoteDataSource.getCharacterList(randomPage).characterList
                    _characterDataFlow.emit(State.Data(characters))
                    localDataSource.clearAndInsert(characters)
                } else if (cachedCharacters.b.isEmpty()) {
                    _characterDataFlow.emit(State.Failure(Exception("No Internet and no cache")))
                }
            } catch (throwable: Throwable) {
                _characterDataFlow.emit(State.Failure(throwable))
            }
        }

    override suspend fun getCharacterList(): Result<Throwable, CharacterList> = apiCall {
        withContext(Dispatchers.IO) {
            remoteDataSource.getCharacterList()
        }
    }

    override suspend fun getCharacterById(id: Int): Result<Throwable, Character> = apiCall {
        withContext(Dispatchers.IO) {
            remoteDataSource.getCharacterById(id)
        }
    }

    override suspend fun getAllCharacters(): Result<Throwable, List<Character>> {
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
}
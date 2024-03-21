package com.example.onelabretrofitapi.domain.useCase

import androidx.paging.PagingData
import com.example.onelabretrofitapi.domain.repository.CharactersRepository
import com.example.onelabretrofitapi.presentation.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CharacterPagingDataUseCase {
    suspend fun execute(): Flow<PagingData<Character>>
}

class CharacterPagingDataUseCaseImpl @Inject constructor(
    private val repo: CharactersRepository
): CharacterPagingDataUseCase {
    override suspend fun execute(): Flow<PagingData<Character>> {
        return repo.getCharacterPagingFlow()
    }

}
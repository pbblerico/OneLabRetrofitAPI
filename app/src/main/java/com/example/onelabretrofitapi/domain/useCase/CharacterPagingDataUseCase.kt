package com.example.onelabretrofitapi.domain.useCase

import androidx.paging.PagingData
import com.example.onelabretrofitapi.domain.repository.CharactersRepository
import com.example.onelabretrofitapi.presentation.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CharacterPagingDataUseCase {
    fun execute(): Flow<PagingData<Character>>
}

class CharacterPagingDataUseCaseImpl @Inject constructor(
    private val repo: CharactersRepository
): CharacterPagingDataUseCase {
    override fun execute(): Flow<PagingData<Character>> {
        return repo.getCharacterPagingFlow()
    }

}
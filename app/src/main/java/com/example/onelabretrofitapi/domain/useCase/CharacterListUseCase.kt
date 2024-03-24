package com.example.onelabretrofitapi.domain.useCase

import com.example.onelabretrofitapi.core.functional.State
import com.example.onelabretrofitapi.domain.repository.CharactersRepository
import com.example.onelabretrofitapi.presentation.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CharacterListUseCase {
//    suspend fun execute(): Result<Throwable, CharacterList>
    val observeCharacterList: Flow<State<List<Character>>>
    suspend fun execute()
}

class CharacterListUseCaseImpl @Inject constructor(
    private val charactersRepo: CharactersRepository
    )
    : CharacterListUseCase {
    override val observeCharacterList: Flow<State<List<Character>>>
        get() = charactersRepo.observeCharacterStateFlow

    override suspend fun execute() {
        return charactersRepo.fetchCharacterList()
    }

}
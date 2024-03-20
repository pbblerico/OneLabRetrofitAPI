package com.example.onelabretrofitapi.domain.useCase

import com.example.onelabretrofitapi.core.State
import com.example.onelabretrofitapi.domain.repository.CharactersRepository
import com.example.onelabretrofitapi.presentation.model.CharacterList
import javax.inject.Inject

interface CharacterListUseCase {
    suspend fun execute(): State<Throwable, CharacterList>

}

class CharacterListUseCaseImpl @Inject constructor(
    private val charactersRepo: CharactersRepository
    )
    : CharacterListUseCase {
    override suspend fun execute(): State<Throwable, CharacterList> {
        return charactersRepo.getCharacterList()
    }

}
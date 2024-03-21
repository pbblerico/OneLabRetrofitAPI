package com.example.onelabretrofitapi.domain.useCase

import com.example.onelabretrofitapi.core.functional.State
import com.example.onelabretrofitapi.domain.repository.CharactersRepository
import com.example.onelabretrofitapi.presentation.model.Character
import javax.inject.Inject

interface CharacterInfoUseCase {
    suspend fun execute(id: Int): State<Throwable, Character>
}

class CharacterInfoUseCaseImpl @Inject constructor(
    private val repo: CharactersRepository
): CharacterInfoUseCase {
    override suspend fun execute(id: Int): State<Throwable, Character> {
        return repo.getCharacterById(id)
    }

}
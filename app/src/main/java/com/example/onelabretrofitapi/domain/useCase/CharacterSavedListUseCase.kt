package com.example.onelabretrofitapi.domain.useCase

import com.example.onelabretrofitapi.domain.repository.CharactersRepository
import com.example.onelabretrofitapi.presentation.model.Character
import javax.inject.Inject

interface CharacterSavedListUseCase {
    suspend fun execute(): Result<List<Character>>
}

class CharacterSavedListUseCaseImpl @Inject constructor(
    private val repo: CharactersRepository
): CharacterSavedListUseCase {
    override suspend fun execute(): Result<List<Character>> {
        return repo.getAllCharacters()
    }

}
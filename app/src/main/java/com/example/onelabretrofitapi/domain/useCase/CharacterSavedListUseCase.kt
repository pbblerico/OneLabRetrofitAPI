package com.example.onelabretrofitapi.domain.useCase

import com.example.onelabretrofitapi.domain.repository.CharactersRepository
import com.example.onelabretrofitapi.presentation.model.Character
import javax.inject.Inject
import com.example.onelabretrofitapi.core.functional.Result

interface CharacterSavedListUseCase {
    suspend fun execute(): Result<Throwable, List<Character>>
}

class CharacterSavedListUseCaseImpl @Inject constructor(
    private val repo: CharactersRepository
): CharacterSavedListUseCase {
    override suspend fun execute(): Result<Throwable, List<Character>> {
        return repo.getAllCharacters()
    }

}
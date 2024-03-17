package com.example.onelabretrofitapi.domain.useCase

import com.example.onelabretrofitapi.data.repository.RoomRepository
import com.example.onelabretrofitapi.presentation.model.Character
import javax.inject.Inject

interface CharacterSavedListUseCase {
    suspend fun execute(): Result<List<Character>>
}

class CharacterSavedListUseCaseImpl @Inject constructor(
    private val repo: RoomRepository
): CharacterSavedListUseCase {
    override suspend fun execute(): Result<List<Character>> {
        return repo.getAllCharacters()
    }

}
package com.example.onelabretrofitapi.domain.useCase

import com.example.onelabretrofitapi.data.repository.RoomRepository
import com.example.onelabretrofitapi.presentation.model.Character
import javax.inject.Inject

interface CharacterSaveUseCase {
    suspend fun execute(character: Character)
}

class CharacterSaveUseCaseImpl @Inject constructor(
    private val repo: RoomRepository
): CharacterSaveUseCase {
    override suspend fun execute(character: Character) {
        repo.insertCharacter(character)
    }

}
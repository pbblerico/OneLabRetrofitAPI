package com.example.onelabretrofitapi.domain.useCase

import com.example.onelabretrofitapi.data.repository.RoomRepository
import javax.inject.Inject

interface CharacterDeleteByIdUseCase {
    suspend fun execute(id: Int)
}

class CharacterDeleteByIdUseCaseImpl @Inject constructor(
    private val repo: RoomRepository
): CharacterDeleteByIdUseCase {
    override suspend fun execute(id: Int) {
        repo.deleteById(id)
    }

}
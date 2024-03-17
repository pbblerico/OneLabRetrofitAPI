package com.example.onelabretrofitapi.data.repository

import com.example.onelabretrofitapi.data.db.CharacterDao
import com.example.onelabretrofitapi.data.mapper.toEntity
import com.example.onelabretrofitapi.data.mapper.toPresentation
import com.example.onelabretrofitapi.presentation.model.Character
import javax.inject.Inject

interface RoomRepository {
    suspend fun getAllCharacters(): Result<List<Character>>

    suspend fun insertCharacter(character: Character)

    suspend fun deleteById(id: Int)
}

class RoomRepositoryImpl @Inject constructor(private val characterDao: CharacterDao) :
    RoomRepository {
    override suspend fun getAllCharacters(): Result<List<Character>> {
        return Result.success(characterDao.getAll().map { it.toPresentation() })
    }

    override suspend fun insertCharacter(character: Character) {
        characterDao.insert(character.toEntity())
    }

    override suspend fun deleteById(id: Int) {
        characterDao.deleteById(id)
    }


}
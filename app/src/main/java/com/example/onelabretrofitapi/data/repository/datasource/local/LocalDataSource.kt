package com.example.onelabretrofitapi.data.repository.datasource.local

import com.example.onelabretrofitapi.data.db.CharacterDao
import com.example.onelabretrofitapi.data.mapper.toEntity
import com.example.onelabretrofitapi.data.mapper.toPresentation
import com.example.onelabretrofitapi.presentation.model.Character
import javax.inject.Inject

interface LocalDataSource {

    suspend fun getAllCharacters(): Result<List<Character>>

    suspend fun insertCharacter(character: Character)

    suspend fun deleteById(id: Int)
}

class LocalDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao
): LocalDataSource {
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
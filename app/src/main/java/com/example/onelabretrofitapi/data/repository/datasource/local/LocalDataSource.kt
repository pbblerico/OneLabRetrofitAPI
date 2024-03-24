package com.example.onelabretrofitapi.data.repository.datasource.local

import com.example.onelabretrofitapi.data.local.CharacterDao
import com.example.onelabretrofitapi.data.mapper.toEntity
import com.example.onelabretrofitapi.data.mapper.toPresentation
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.core.functional.Result
import javax.inject.Inject

interface LocalDataSource {

    suspend fun getAllCharacters(): Result<Throwable, List<Character>>

    suspend fun insertCharacter(character: Character)

    suspend fun deleteById(id: Int)

    suspend fun insertCharacterList(characters: List<Character>)

    suspend fun clearAndInsert(characters: List<Character>)
}

class LocalDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao
): LocalDataSource {
    override suspend fun getAllCharacters(): Result<Throwable, List<Character>> {
        return Result.Success(characterDao.getAll().map { it.toPresentation() })
    }

    override suspend fun insertCharacter(character: Character) {
        characterDao.insert(character.toEntity())
    }

    override suspend fun deleteById(id: Int) {
        characterDao.deleteById(id)
    }

    override suspend fun insertCharacterList(characters: List<Character>) {
        characterDao.insertList(characters.map { it.toEntity() })
    }

    override suspend fun clearAndInsert(characters: List<Character>) {
        characterDao.clearAndInsert(characters.map { it.toEntity() })
    }


}
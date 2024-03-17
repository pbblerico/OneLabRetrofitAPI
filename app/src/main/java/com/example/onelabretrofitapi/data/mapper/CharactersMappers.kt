package com.example.onelabretrofitapi.data.mapper

import com.example.onelabretrofitapi.data.db.CharacterEntity
import com.example.onelabretrofitapi.data.model.CharacterDTO
import com.example.onelabretrofitapi.data.model.CharacterListDTO
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.model.CharacterList

internal fun CharacterDTO.toCharacter() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    image = image
)

internal fun CharacterListDTO.toCharacterList() = CharacterList(
    characterList = results.map { it.toCharacter() }
)

internal fun Character.toEntity() = CharacterEntity(
    characterId = id,
    characterStatus = status,
    characterSpecies = species,
    characterGender = gender,
    characterName = name,
    characterImage = image
)

internal fun CharacterEntity.toPresentation() = Character(
    id = characterId,
    name = characterName,
    status = characterStatus,
    species = characterSpecies,
    gender = characterGender,
    image = characterImage
)
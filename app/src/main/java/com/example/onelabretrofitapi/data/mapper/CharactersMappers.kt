package com.example.onelabretrofitapi.data.mapper

import com.example.onelabretrofitapi.data.model.CharacterDTO
import com.example.onelabretrofitapi.data.model.CharacterListDTO
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.model.CharacterList

internal fun CharacterDTO.toCharacter() = Character(
    id = id,
    name= name,
    status = status,
    species = species,
    gender = gender,
    image = image
)

internal fun CharacterListDTO.toCharacterList() = CharacterList(
    characterList = results.map { it.toCharacter() }
)
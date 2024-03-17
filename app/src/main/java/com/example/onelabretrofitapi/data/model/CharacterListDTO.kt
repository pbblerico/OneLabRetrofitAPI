package com.example.onelabretrofitapi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterListDTO(
    @SerialName("info")
    val info: InfoDTO,
    @SerialName("results")
    val results: List<CharacterDTO>,
)

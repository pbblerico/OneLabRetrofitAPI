package com.example.onelabretrofitapi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Url


@Serializable
data class CharacterDTO(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("status")
    val status: String?,
    @SerialName("species")
    val species: String?,
    @SerialName("gender")
    val gender: String?,
    @Url
    @SerialName("image")
    val image: String?
)

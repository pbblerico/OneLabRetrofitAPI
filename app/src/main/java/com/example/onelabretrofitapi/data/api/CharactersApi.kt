package com.example.onelabretrofitapi.data.api

import com.example.onelabretrofitapi.data.model.CharacterDTO
import com.example.onelabretrofitapi.data.model.CharacterListDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(): CharacterListDTO

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterDTO
}
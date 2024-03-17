package com.example.onelabretrofitapi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Url


@Serializable
data class InfoDTO(
    @SerialName("count")
    val count: Int?,
    @SerialName("pages")
    val pages: Int?,
    @Url
    @SerialName("next")
    val next: String?,
    @Url
    @SerialName("prev")
    val prev: String?,
)

package com.example.coordinadoraapp.data.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUserLoginDTO(
    @SerialName("usuario") val usuario: String,
    @SerialName("password") val password: String
)

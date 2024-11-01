package com.example.coordinadoraapp.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginUserDTO(
    @SerialName("isError") val isError: Boolean,
    @SerialName("data") val data: String,
    @SerialName("perido_validacion") val perido_validacion: Int
)

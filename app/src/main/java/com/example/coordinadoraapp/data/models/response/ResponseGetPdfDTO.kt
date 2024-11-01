package com.example.coordinadoraapp.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetPdfDTO(
    @SerialName("isError") val isError: Boolean,
    @SerialName("base64") val base64: String,
    @SerialName("posiciones") val posiciones: List<PositionDTO>
)

@Serializable
data class PositionDTO(
    @SerialName("latitud") val latitud: Double,
    @SerialName("longitud") val longitud: Double
)

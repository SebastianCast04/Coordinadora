package com.example.coordinadoraapp.domain.repository

import com.example.coordinadoraapp.data.models.response.ResponseGetPdfDTO

interface MenuRepository {
    suspend fun getPDF(): ResponseGetPdfDTO
}
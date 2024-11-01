package com.example.coordinadoraapp.data.remote

import com.example.coordinadoraapp.data.models.response.ResponseGetPdfDTO

interface MenuService {
    suspend fun getImage(): ResponseGetPdfDTO
}
package com.example.coordinadoraapp.domain.repository

import com.example.coordinadoraapp.data.models.request.RequestUserLoginDTO
import com.example.coordinadoraapp.data.models.response.ResponseLoginUserDTO

interface AuthRepository {
    suspend fun loginUser(userLoginDTO: RequestUserLoginDTO): ResponseLoginUserDTO
}
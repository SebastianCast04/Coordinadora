package com.example.coordinadoraapp.data.remote

import com.example.coordinadoraapp.data.models.request.RequestUserLoginDTO
import com.example.coordinadoraapp.data.models.response.ResponseLoginUserDTO

interface AuthService {
    suspend fun loginUser(userLoginDTO: RequestUserLoginDTO): ResponseLoginUserDTO
}
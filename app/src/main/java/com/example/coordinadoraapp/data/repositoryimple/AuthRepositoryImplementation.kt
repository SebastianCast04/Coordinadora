package com.example.coordinadoraapp.data.repositoryimple

import com.example.coordinadoraapp.data.models.request.RequestUserLoginDTO
import com.example.coordinadoraapp.data.models.response.ResponseLoginUserDTO
import com.example.coordinadoraapp.data.remote.AuthService
import com.example.coordinadoraapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun loginUser(userLoginDTO: RequestUserLoginDTO): ResponseLoginUserDTO {
        return authService.loginUser(userLoginDTO)
    }
}
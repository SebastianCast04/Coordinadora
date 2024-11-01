package com.example.coordinadoraapp.domain.usecase

import com.example.coordinadoraapp.data.models.request.RequestUserLoginDTO
import com.example.coordinadoraapp.data.models.response.ResponseLoginUserDTO
import com.example.coordinadoraapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userLoginDTO: RequestUserLoginDTO): ResponseLoginUserDTO {
        return authRepository.loginUser(userLoginDTO)
    }
}
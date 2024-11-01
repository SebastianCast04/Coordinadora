package com.example.coordinadoraapp.domain.usecase

import com.example.coordinadoraapp.data.local.database.Usuario
import com.example.coordinadoraapp.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(usuario: Usuario) {
        userRepository.saveUser(usuario)
    }
}
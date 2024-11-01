package com.example.coordinadoraapp.domain.repository

import com.example.coordinadoraapp.data.local.database.Usuario

interface UserRepository {
    suspend fun saveUser(usuario: Usuario)
    suspend fun getUserByCredentials(email: String, password: String): Usuario?
}
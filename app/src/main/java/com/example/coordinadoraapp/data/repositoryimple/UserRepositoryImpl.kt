package com.example.coordinadoraapp.data.repositoryimple

import com.example.coordinadoraapp.data.local.database.Usuario
import com.example.coordinadoraapp.data.local.database.UserDao
import com.example.coordinadoraapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun saveUser(usuario: Usuario) {
        userDao.insertUsuario(usuario)
    }

    override suspend fun getUserByCredentials(email: String, password: String): Usuario? {
        return  userDao.login(email, password)
    }


}
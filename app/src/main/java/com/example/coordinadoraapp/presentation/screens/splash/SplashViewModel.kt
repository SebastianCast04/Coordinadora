package com.example.coordinadoraapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import com.example.coordinadoraapp.data.local.database.Usuario
import com.example.coordinadoraapp.domain.usecase.GetUserByCredentialsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserByCredentialsUseCase: GetUserByCredentialsUseCase
) : ViewModel(){

    private var _usuario: Usuario? = null

    suspend fun checkUser(email: String, password: String): Usuario? {
        _usuario = getUserByCredentialsUseCase(email, password)
        return _usuario
    }

}
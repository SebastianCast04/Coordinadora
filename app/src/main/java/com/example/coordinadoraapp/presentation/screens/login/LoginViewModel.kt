package com.example.coordinadoraapp.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.coordinadoraapp.data.local.database.Usuario
import com.example.coordinadoraapp.data.models.request.RequestUserLoginDTO
import com.example.coordinadoraapp.data.models.response.ResponseLoginUserDTO
import com.example.coordinadoraapp.domain.repository.AuthRepository
import com.example.coordinadoraapp.domain.usecase.SaveUserUseCase
import com.example.coordinadoraapp.presentation.states.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {

    // StateFlow para manejar el estado del login
    private val _stateFlowLogin = MutableStateFlow<ResponseState<ResponseLoginUserDTO>>(ResponseState())
    val stateFlowLogin: StateFlow<ResponseState<ResponseLoginUserDTO>> = _stateFlowLogin.asStateFlow()

    fun loginUser(usuario: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) { // Coroutina para lanzar la petición en el hilo secundario y así liberar el hilo principal

            val userLoginDTO = RequestUserLoginDTO(
                usuario = usuario,
                password = password
            )
            _stateFlowLogin.value = ResponseState(isLoading = true) // Indicar que la petición está en curso

            try {
                val result = authRepository.loginUser(userLoginDTO)
                val user = Usuario(
                    id = 1,
                    email = usuario,
                    password = password
                )
                saveUserUseCase(user)
                println("Soy la respuesta $result")
                _stateFlowLogin.value = ResponseState(data = result) // Actualizar el estado con los datos obtenidos

            } catch (e: Exception) {
                _stateFlowLogin.value = ResponseState(error = "Login error: ${e.message}") // Manejar el error
            }
        }
    }
}

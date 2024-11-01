package com.example.coordinadoraapp

import com.example.coordinadoraapp.data.models.response.ResponseLoginUserDTO
import com.example.coordinadoraapp.domain.repository.AuthRepository
import com.example.coordinadoraapp.domain.usecase.SaveUserUseCase
import com.example.coordinadoraapp.presentation.screens.login.LoginViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.coordinadoraapp.data.models.request.RequestUserLoginDTO
import com.example.coordinadoraapp.presentation.states.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule() // Para ejecutar las LiveData en el mismo hilo

    @Mock
    private lateinit var authRepository: AuthRepository

    @Mock
    private lateinit var saveUserUseCase: SaveUserUseCase

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        // Inicializamos Mockito
        MockitoAnnotations.openMocks(this)

        // Inicializamos el view model
        loginViewModel = LoginViewModel(authRepository, saveUserUseCase)

        // Usamos un Dispatcher de prueba
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `test login success updates state with user data`() = runTest {
        // Mock de la respuesta exitosa del login
        val mockResponse = ResponseLoginUserDTO(isError = false, data = "token123", perido_validacion = 60)

        // Simulamos que el repositorio devuelve la respuesta simulada
        whenever(authRepository.loginUser(any<RequestUserLoginDTO>())).thenReturn(mockResponse)

        // Ejecutamos el login
        val email = "user@test.com"
        val password = "password"
        loginViewModel.loginUser(email, password) // Asegúrate de que no sean nulos

        // Verificamos el estado de loading inicial
        loginViewModel.stateFlowLogin.test {
            assertEquals(ResponseState<ResponseLoginUserDTO>(isLoading = true), awaitItem())
            // Verificamos el estado final con los datos obtenidos
            val finalState = awaitItem()
            assertNotNull(finalState)
            assertEquals(ResponseState<ResponseLoginUserDTO>(data = mockResponse), finalState)
        }
    }


    @Test
    fun `test login failure updates state with error`() = runTest {
        // Simulamos que el repositorio lanza una excepción
        whenever(authRepository.loginUser(any())).thenThrow(Exception("Login failed"))

        // Ejecutamos el login
        loginViewModel.loginUser("user@test.com", "password")

        // Verificamos el estado de error
        loginViewModel.stateFlowLogin.test<ResponseState<ResponseLoginUserDTO>> {
            assertEquals(ResponseState<ResponseState<ResponseLoginUserDTO>>(isLoading = true), awaitItem())
            assertEquals(ResponseState<ResponseState<ResponseLoginUserDTO>>(error = "Login error: Login failed"), awaitItem())
        }
    }
}

package com.example.coordinadoraapp

import com.example.coordinadoraapp.data.models.request.RequestUserLoginDTO
import com.example.coordinadoraapp.data.models.response.ResponseLoginUserDTO
import com.example.coordinadoraapp.domain.repository.AuthRepository
import com.example.coordinadoraapp.domain.usecase.LoginUseCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever


class LoginUseCaseTest {


    // Dependencia simulada del repositorio
    @Mock
    private lateinit var authRepository: AuthRepository

    // El caso de uso que vamos a probarr
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        // Inicializamos Mockito
        MockitoAnnotations.openMocks(this)
        loginUseCase = LoginUseCase(authRepository)
    }

    @Test
    fun `test successful login`() = runTest {
        // Dato simulado de respuesta para el login exitoso
        val mockResponse = ResponseLoginUserDTO(isError = false, data = "token123", perido_validacion = 60)
        // Simulamos que el repositorio devuelve una respuesta exitosa
        whenever(authRepository.loginUser(any())).thenReturn(mockResponse)

        // Ejecutamos el caso de uso
        val result = loginUseCase(RequestUserLoginDTO(usuario = "user@test.com", password = "password"))

        // Verificamos que el resultado sea el esperado
        assertEquals(mockResponse, result)
    }

    @Test(expected = Exception::class)
    fun `test login failure throws exception`() = runTest {
        // Simulamos que el repositorio lanza una excepción
        whenever(authRepository.loginUser(any())).thenThrow(Exception("Login failed"))

        // Ejecutamos el caso de uso y esperamos una excepción
        loginUseCase(RequestUserLoginDTO(usuario = "user@test.com", password = "password"))
    }
}
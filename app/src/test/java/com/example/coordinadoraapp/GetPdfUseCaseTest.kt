package com.example.coordinadoraapp

import com.example.coordinadoraapp.data.models.response.PositionDTO
import com.example.coordinadoraapp.data.models.response.ResponseGetPdfDTO
import com.example.coordinadoraapp.domain.repository.MenuRepository
import com.example.coordinadoraapp.domain.usecase.GetPdfUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class GetPdfUseCaseTest {

    @Mock
    private lateinit var menuRepository: MenuRepository

    private lateinit var getPdfUseCase: GetPdfUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getPdfUseCase = GetPdfUseCase(menuRepository)
    }

    @Test
    fun `invoke calls getPDF on repository`() = runBlocking {
        val expectedResponse = ResponseGetPdfDTO(false, "base64string", listOf(PositionDTO(1.0, 2.0)))
        whenever(menuRepository.getPDF()).thenReturn(expectedResponse)

        val result = getPdfUseCase()

        assertEquals(expectedResponse, result)
    }
}
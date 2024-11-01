package com.example.coordinadoraapp

import com.example.coordinadoraapp.data.models.response.PositionDTO
import com.example.coordinadoraapp.data.models.response.ResponseGetPdfDTO
import com.example.coordinadoraapp.data.remote.MenuService
import com.example.coordinadoraapp.data.repositoryimple.MenuRepositoryImplementation
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class MenuRepositoryImplementationTest {

    @Mock
    private lateinit var menuService: MenuService

    private lateinit var menuRepository: MenuRepositoryImplementation

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        menuRepository = MenuRepositoryImplementation(menuService)
    }

    @Test
    fun `getPDF returns ResponseGetPdfDTO from MenuService`() = runBlocking {
        val expectedResponse = ResponseGetPdfDTO(false, "base64string", listOf(PositionDTO(1.0, 2.0)))
        whenever(menuService.getImage()).thenReturn(expectedResponse)

        val result = menuRepository.getPDF()

        assertEquals(expectedResponse, result)
    }
}
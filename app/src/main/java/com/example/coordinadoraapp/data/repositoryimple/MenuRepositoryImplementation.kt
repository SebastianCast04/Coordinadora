package com.example.coordinadoraapp.data.repositoryimple

import com.example.coordinadoraapp.data.models.response.ResponseGetPdfDTO
import com.example.coordinadoraapp.data.remote.MenuService
import com.example.coordinadoraapp.domain.repository.MenuRepository
import javax.inject.Inject

class MenuRepositoryImplementation @Inject constructor(
    private val menuService: MenuService
) : MenuRepository {
    override suspend fun getPDF(): ResponseGetPdfDTO {
        return menuService.getImage()
    }
}
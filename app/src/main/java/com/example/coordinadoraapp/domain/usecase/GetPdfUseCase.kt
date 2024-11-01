package com.example.coordinadoraapp.domain.usecase

import com.example.coordinadoraapp.data.models.response.ResponseGetPdfDTO
import com.example.coordinadoraapp.domain.repository.MenuRepository
import javax.inject.Inject

class GetPdfUseCase @Inject constructor(
    private val menuRepository: MenuRepository
) {
    suspend operator fun invoke(): ResponseGetPdfDTO {
        return menuRepository.getPDF()
    }
}
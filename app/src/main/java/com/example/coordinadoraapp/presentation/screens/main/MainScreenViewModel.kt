package com.example.coordinadoraapp.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coordinadoraapp.data.models.response.ResponseGetPdfDTO
import com.example.coordinadoraapp.domain.usecase.GetPdfUseCase
import com.example.coordinadoraapp.presentation.states.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getPdfUseCase: GetPdfUseCase
) : ViewModel() {

    private val _stateFlowImagen = MutableStateFlow<ResponseState<ResponseGetPdfDTO>>(ResponseState())
    val stateFlowImagen: StateFlow<ResponseState<ResponseGetPdfDTO>> = _stateFlowImagen.asStateFlow()

    fun getImages() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlowImagen.value = ResponseState(isLoading = true)
            try {
                val result = getPdfUseCase()
                _stateFlowImagen.value = ResponseState(data = result)
                println("SOY LA RESPUESTA DEL PDF $result")
            } catch (e: Exception) {
                _stateFlowImagen.value = ResponseState(error = "Error al obtener la imagen: ${e.message}")
            }
        }
    }
}
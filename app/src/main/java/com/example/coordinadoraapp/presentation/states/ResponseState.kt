package com.example.coordinadoraapp.presentation.states

data class ResponseState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String? = null
)
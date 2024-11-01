package com.example.coordinadoraapp.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ThemeApp(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography(),
        shapes = MaterialTheme.shapes,
        content = {
            content()
        })
}
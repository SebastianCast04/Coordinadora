package com.example.coordinadoraapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.coordinadoraapp.presentation.screens.login.LoginViewModel
import com.example.coordinadoraapp.presentation.screens.splash.SplashScreen
import com.example.coordinadoraapp.presentation.theme.ThemeApp
import com.example.coordinadoraapp.presentation.theme.colorScheme

@Composable
fun App() {
    ThemeApp {
        Box(
            modifier = Modifier.background(color = colorScheme.inverseSurface).fillMaxSize().windowInsetsPadding(
                WindowInsets.safeDrawing)
        ) {
            Navigator(screen = SplashScreen()) { navigator ->
                SlideTransition(navigator)
            }
        }
    }
}
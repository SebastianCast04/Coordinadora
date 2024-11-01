package com.example.coordinadoraapp.presentation.enums


data class NavigationItem(
    val title: String,
    val icon: Int,
    val badgeCount: Int? = null,
    val menu: Int
)
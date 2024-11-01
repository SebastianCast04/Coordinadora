package com.example.coordinadoraapp.domain.util

fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex(pattern = "^\\S+@\\S+\\.\\S+\$")
    return email.matches(emailRegex) && email.length <=256
}

fun isValidPassword(password: String): Boolean {
    return (password.length >= 8) && (password.length) <=20 && (password.any { it.isUpperCase() }) && (password.any { !it.isLetterOrDigit() })
}
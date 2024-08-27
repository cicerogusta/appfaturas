package com.example.faturas_app.model

import org.jetbrains.annotations.NotNull

data class LoginRequest(
    val username: String,
    val password: String
){
    init {
        require(username.isNotBlank()) { "Username is required" }
        require(password.isNotBlank()) { "Password is required" }
    }
}

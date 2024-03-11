package ru.playzone.database.users


//DTO - data transfer object
data class UserDTO (
    val login: String,
    val password: String,
    val email: String?,
    val username: String
)
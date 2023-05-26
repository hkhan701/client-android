package com.example.client_android.models

data class User(
    val id: String,
    val fullname: String,
    val email: String
) {
    val initials: String
        get() {
           val words = fullname.split(" ")
            return words.joinToString("") { it.first().toString().uppercase() }
        }
}
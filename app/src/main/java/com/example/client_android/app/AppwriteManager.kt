package com.example.client_android.app

import io.appwrite.Client
import io.appwrite.exceptions.AppwriteException
import io.appwrite.services.Account
import java.util.UUID

class AppwriteManager(private val appWriteClient: Client) {

    private val accountService = Account(appWriteClient)

    suspend fun createAccount(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        try {
            val user = accountService.create(
                email = email,
                password = password,
                userId = UUID.randomUUID().toString()
            )

            print(user.toMap())

//            onSuccess.invoke()
        } catch (e: AppwriteException) {
            val errorMessage = e.message
            onError.invoke(errorMessage ?: "Unknown error occurred")
        }
    }

    suspend fun createSession(email: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        try {
            val response = accountService.createEmailSession(
                email = email,
                password = password
            )
            onSuccess.invoke(response.id)
        } catch (e: AppwriteException) {
            val errorMessage = e.message
            onError.invoke(errorMessage ?: "Unknown error occurred")
        }
    }
}
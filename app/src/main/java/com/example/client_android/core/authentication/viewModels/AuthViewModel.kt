package com.example.client_android.core.authentication.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client_android.models.User
import io.appwrite.Client
import io.appwrite.exceptions.AppwriteException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AuthViewModel(
    private val appwriteClient: Client,
    private val sharedPreferences: SharedPreferences
    ) : ViewModel() {

    private var isLoggedIn: Boolean = false
    private var currentUser: User? = null

    init {
        checkLoggedIn()
    }

    private fun checkLoggedIn() {
        val isUserLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        isLoggedIn = isUserLoggedIn
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                // Perform sign-in logic using AppwriteClient
                // Example: appwriteClient.account.createSession(email, password)
                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
            } catch (e: AppwriteException) {
                // Handle sign-in failure

            }
        }
    }

    fun createUser(email: String, password: String, fullName: String) {
        viewModelScope.launch {
            try {
                // Perform user creation logic using AppwriteClient
                // Example: appwriteClient.account.create(email, password)
                // After successful user creation, update isLoggedIn and currentUser values
                currentUser = User("find a unique id",fullName, email)
            } catch (e: AppwriteException) {
                // Handle user creation failure
                currentUser = null
            }
        }
    }

    fun signOut() {
        // Perform sign-out logic
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
        currentUser = null
    }

    fun fetchUser() {
        viewModelScope.launch {
            try {
                // Fetch user data using AppwriteClient
                // Example: appwriteClient.account.get()
                // Update currentUser with fetched user data
//                currentUser = User("find a unique id",fullName, email)
            } catch (e: AppwriteException) {
                // Handle fetch user failure
                currentUser = null
            }
        }
    }
}

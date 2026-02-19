package com.example.minutanutricional.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.minutanutricional.data.AuthManager

class AuthViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun login(email: String, pass: String, onSuccess: () -> Unit) {
        isLoading = true
        errorMessage = null

        AuthManager.auth
            .signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                isLoading = false
                onSuccess()
            }
            .addOnFailureListener {
                isLoading = false
                errorMessage = "Error: Credenciales inválidas o problema de red."
            }
    }

    fun isUserLoggedIn(): Boolean {
        return AuthManager.auth.currentUser != null
    }

    fun logout() {
        AuthManager.auth.signOut()
    }


}

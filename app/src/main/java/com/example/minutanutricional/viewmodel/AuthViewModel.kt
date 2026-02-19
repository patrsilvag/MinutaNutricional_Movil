package com.example.minutanutricional.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.minutanutricional.data.AuthManager

// Estos son esenciales para manejar la autenticación y las tareas
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.tasks.Task

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

    fun registrar(email: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        isLoading = true
        AuthManager.auth.createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                isLoading = false
                onSuccess()
            }
            .addOnFailureListener { exception ->
                isLoading = false
                // Aquí traducimos los errores más comunes de Firebase
                val mensajeEspanol = when {
                    exception.message?.contains("already in use") == true ->
                        "Este correo ya está registrado."
                    exception.message?.contains("badly formatted") == true ->
                        "El formato del correo no es válido."
                    exception.message?.contains("at least 6 characters") == true ->
                        "La contraseña debe tener al menos 6 caracteres."
                    else -> "Error al crear cuenta: ${exception.localizedMessage}"
                }
                onError(mensajeEspanol)
            }
    }

    fun recuperarClave(email: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        isLoading = true
        AuthManager.auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                isLoading = false
                onSuccess()
            }
            .addOnFailureListener { exception ->
                isLoading = false
                val errorMsg = if (exception.message?.contains("no user record") == true) {
                    "El correo no está registrado."
                } else {
                    "Error al enviar correo: ${exception.localizedMessage}"
                }
                onError(errorMsg)
            }
    }
}

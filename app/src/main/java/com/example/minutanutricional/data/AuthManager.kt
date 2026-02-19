package com.example.minutanutricional.data

import com.google.firebase.auth.FirebaseAuth

object AuthManager {
    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
}

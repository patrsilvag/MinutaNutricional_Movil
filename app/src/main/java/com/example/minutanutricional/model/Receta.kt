package com.example.minutanutricional.model

// Asegúrate de que cada campo tenga un valor por defecto
data class Receta(
    val dia: String = "",
    val nombre: String = "",
    val calorias: Int = 0,
    val recomendacion: String? = null
) {
    // Firebase necesita este constructor vacío que Kotlin genera
    // automáticamente si todos los parámetros tienen valores por defecto.
}
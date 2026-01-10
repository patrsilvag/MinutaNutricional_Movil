package com.example.minutanutricional.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.minutanutricional.model.Receta

import com.example.minutanutricional.screens.PantallaLogin
import com.example.minutanutricional.screens.PantallaRegistro
import com.example.minutanutricional.screens.PantallaRecuperar
import com.example.minutanutricional.screens.PantallaMinuta
import com.example.minutanutricional.screens.PantallaDetalle


// --------------------
// NAVEGACIÓN SIMPLE
// --------------------
@Composable
fun AppNavigation() {
    var pantallaActual by remember { mutableStateOf("login") }
    var recetaSeleccionada by remember { mutableStateOf<Receta?>(null) }


    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background) {
        when (pantallaActual) {
            "login" -> PantallaLogin(
                onLoginSuccess = { pantallaActual = "menu" },
                onIrARegistro = { pantallaActual = "registro" },
                onIrARecuperar = { pantallaActual = "recuperar" }
            )

            "registro" -> PantallaRegistro(onVolver = { pantallaActual = "login" })

            "recuperar" -> PantallaRecuperar(onVolver = { pantallaActual = "login" })

            "menu" -> PantallaMinuta(
                onLogout = { pantallaActual = "login" },
                onVerDetalle = { receta: Receta ->
                    recetaSeleccionada = receta
                    pantallaActual = "detalle"
                }
            )

            "detalle" -> {
                val r = recetaSeleccionada
                if (r == null) {
                    pantallaActual = "menu"
                } else {
                    PantallaDetalle(
                        receta = r,
                        onVolver = { pantallaActual = "menu" }
                    )
                }
            }
        }
    }
}
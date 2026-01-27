package com.example.minutanutricional.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minutanutricional.components.CurvedBackground

// --------------------
// RECUPERAR
// --------------------
@Composable
fun PantallaRecuperar(onVolver: () -> Unit) {
    var correo by remember { mutableStateOf("") }
    var errorCorreo by remember { mutableStateOf<String?>(null) }
    var mensajeOk by remember { mutableStateOf<String?>(null) }

    fun esCorreoValido(c: String): Boolean {
        val t = c.trim()
        return t.contains("@") && t.contains(".") && t.length >= 6
    }


    // 1. Box para permitir el fondo (igual que en Registro)
    Box(modifier = Modifier.fillMaxSize()) {

        // 2. Fondo curvo
        CurvedBackground()

        // 3. Columna con la misma configuración que Registro
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp), // Cambiado de 32.dp a 24.dp para igualar Registro
            verticalArrangement = Arrangement.Center // Centrado automático
        ) {
            // Se eliminó el Spacer de 180.dp para que no empuje el texto hacia abajo

            Text(
                text = "Recuperar Clave",
                fontSize = 28.sp, // Aumentado a 28.sp para igualar el título de Registro
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(20.dp)) // Aumentado a 20.dp para igualar Registro

            OutlinedTextField(
                value = correo,
                onValueChange = {
                    correo = it
                    errorCorreo = null
                    mensajeOk = null
                },
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = errorCorreo != null,
                supportingText = { errorCorreo?.let { Text(it) } }
            )


            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val c = correo.trim()

                    errorCorreo = when {
                        c.isBlank() -> "Ingrese su correo"
                        !esCorreoValido(c) -> "Correo no válido"
                        else -> null
                    }

                    if (errorCorreo == null) {
                        mensajeOk = "Código enviado (simulado)"
                        // Si quieres volver inmediatamente, deja onVolver() aquí.
                        // Si quieres que el usuario vea el mensaje, NO vuelvas altiro.
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) { Text("ENVIAR CÓDIGO") }

            Spacer(modifier = Modifier.height(10.dp))
            mensajeOk?.let {
                Text(text = it, color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
            }

        }
    }
}
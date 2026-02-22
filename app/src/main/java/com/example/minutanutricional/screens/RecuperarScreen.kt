package com.example.minutanutricional.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minutanutricional.components.CurvedBackground
import com.example.minutanutricional.viewmodel.AuthViewModel

// --------------------
// RECUPERAR CLAVE
// --------------------
@Composable
fun PantallaRecuperar(
    authViewModel: AuthViewModel,
    onVolverLogin: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var errorCorreo by remember { mutableStateOf<String?>(null) }
    var mensajeOk by remember { mutableStateOf<String?>(null) }

    fun esCorreoValido(c: String): Boolean =
        c.contains("@") && c.contains(".") && c.length >= 6

    Box(modifier = Modifier.fillMaxSize()) {

        CurvedBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Recuperar Clave",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

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
                    if (esCorreoValido(c)) {
                        authViewModel.recuperarClave(
                            email = c,
                            onSuccess = {
                                mensajeOk = "Correo enviado correctamente"
                                errorCorreo = null
                            },
                            onError = { error ->
                                errorCorreo = error
                                mensajeOk = null
                            }
                        )
                    } else {
                        errorCorreo = "Correo no válido"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = !authViewModel.isLoading
            ) {
                Text(if (authViewModel.isLoading) "ENVIANDO..." else "ENVIAR CORREO")
            }

            Spacer(modifier = Modifier.height(10.dp))

            mensajeOk?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 13.sp
                )

                // ⏱️ Volver automático al login
                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(1500)
                    onVolverLogin()
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextButton(
                onClick = onVolverLogin,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Volver al login")
            }
        }
    }
}
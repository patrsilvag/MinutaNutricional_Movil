package com.example.minutanutricional.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minutanutricional.R
import com.example.minutanutricional.components.CurvedBackground

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton



@Composable
fun PantallaLogin(
    onLoginSuccess: () -> Unit,
    onIrARegistro: () -> Unit,
    onIrARecuperar: () -> Unit
) {
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var errorUsuario by remember { mutableStateOf<String?>(null) }
    var errorPassword by remember { mutableStateOf<String?>(null) }
    var errorGeneral by remember { mutableStateOf<String?>(null) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Fondo curvo (arriba/abajo)
        CurvedBackground()

        // Layout pro: contenido bajo header
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //  Header invisible que “reserva” espacio bajo la curva
            Spacer(modifier = Modifier.height(120.dp))

            //  Logo justo bajo la curva
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_minuta),
                    contentDescription = "Logo Minuta Semanal",
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                        .height(56.dp),
                    contentScale = ContentScale.Fit
                )
            }



            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "NutriApp",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = usuario,
                onValueChange = {
                    usuario = it
                    errorUsuario = null
                    errorGeneral = null
                },
                label = { Text("Usuario") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorUsuario != null,
                supportingText = {
                    errorUsuario?.let { Text(it) }
                }
            )


            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    errorPassword = null
                    errorGeneral = null
                },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorPassword != null,
                supportingText = {
                    errorPassword?.let { Text(it) }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,
                            contentDescription = if (passwordVisible)
                                "Ocultar contraseña"
                            else
                                "Mostrar contraseña"
                        )
                    }
                }
            )


            Spacer(modifier = Modifier.height(22.dp))

            Button(
                onClick = {
                    val u = usuario.trim()
                    val p = password.trim()

                    errorUsuario = when {
                        u.isBlank() -> "Ingrese usuario"
                        else -> null
                    }

                    errorPassword = when {
                        p.isBlank() -> "Ingrese contraseña"
                        p.length < 4 -> "Contraseña muy corta"
                        else -> null
                    }

                    if (errorUsuario == null && errorPassword == null) {
                        onLoginSuccess()
                    } else {
                        errorGeneral = "Revise los campos marcados"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("INGRESAR")
            }


            Spacer(modifier = Modifier.height(16.dp))

            errorGeneral?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
            }


            Text(
                text = "¿Olvidaste tu contraseña?",
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.clickable { onIrARecuperar() }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Crear cuenta nueva",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onIrARegistro() }
            )

            // Empuja el bloque hacia arriba (evita que el bottom curve “aplasté” el contenido)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

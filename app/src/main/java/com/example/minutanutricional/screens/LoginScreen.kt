package com.example.minutanutricional.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --------------------
// LOGIN
// --------------------
@Composable
fun PantallaLogin(
    onLoginSuccess: () -> Unit,
    onIrARegistro: () -> Unit,
    onIrARecuperar: () -> Unit
) {
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("NutriApp", fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onLoginSuccess,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("INGRESAR")
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "¿Olvidaste tu contraseña?",
            modifier = Modifier.clickable { onIrARecuperar() }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Crear cuenta nueva",
            modifier = Modifier.clickable { onIrARegistro() }
        )
    }
}
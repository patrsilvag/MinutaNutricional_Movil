package com.example.minutanutricional.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --------------------
// REGISTRO
// --------------------
@Composable
fun PantallaRegistro(onVolver: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var aceptaTerminos by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Registro", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre Completo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = aceptaTerminos,
                onCheckedChange = { aceptaTerminos = it }
            )
            Text("Acepto términos y condiciones")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("GUARDAR Y VOLVER")
        }
    }
}
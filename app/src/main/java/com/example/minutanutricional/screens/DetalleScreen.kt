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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minutanutricional.model.Receta
import com.example.minutanutricional.components.TablaNutricional

// --------------------
// DETALLE
// --------------------
@Composable
fun PantallaDetalle(
    receta: Receta,
    onVolver: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Detalle", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = onVolver) { Text("Volver") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TablaNutricional(receta)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("VOLVER AL MENÚ")
        }
    }
}
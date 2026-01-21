package com.example.minutanutricional.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minutanutricional.model.Receta
import com.example.minutanutricional.components.TablaNutricional
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


// --------------------
// DETALLE
// --------------------
@Composable
fun PantallaDetalle(
    receta: Receta,
    onVolver: () -> Unit
) {
    var favorito by remember { mutableStateOf(false) }
    var mostrarRecomendacion by remember { mutableStateOf(false) }


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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = receta.nombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            IconToggleButton(
                checked = favorito,
                onCheckedChange = { favorito = it }
            ) {
                Icon(
                    imageVector = if (favorito) Icons.Filled.Star else Icons.Outlined.StarBorder,
                    contentDescription = null
                )
            }
        }

        TablaNutricional(receta)
        Spacer(modifier = Modifier.height(12.dp))

        TextButton(
            onClick = { mostrarRecomendacion = !mostrarRecomendacion },
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(if (mostrarRecomendacion) "Ocultar recomendación" else "Ver recomendación")
        }

        if (mostrarRecomendacion) {
            Text(
                text = receta.recomendacion ?: "Sin recomendación disponible"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("VOLVER AL MENÚ")
        }
    }
}
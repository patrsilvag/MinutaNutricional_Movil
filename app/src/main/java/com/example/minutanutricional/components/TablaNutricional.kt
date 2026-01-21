package com.example.minutanutricional.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.minutanutricional.components.FilaTabla
import com.example.minutanutricional.model.Receta

// --------------------
// TABLA (DETALLE)
// --------------------
@Composable
fun TablaNutricional(receta: Receta) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Dato", fontWeight = FontWeight.Bold)
            Text("Valor", fontWeight = FontWeight.Bold)
        }

        HorizontalDivider()
        FilaTabla("Día", receta.dia)
        HorizontalDivider()
        FilaTabla("Receta", receta.nombre)
        HorizontalDivider()
        FilaTabla("Calorías", "${receta.calorias} Kcal")
       // HorizontalDivider()
       // FilaTabla("Recomendación", receta.recomendacion ?: "Sin recomendación disponible")

    }
}
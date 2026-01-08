package com.example.minutanutricional.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minutanutricional.model.Receta

// --------------------
// TARJETA
// --------------------
@Composable
fun TarjetaReceta(
    receta: Receta,
    onCocinar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(receta.dia, fontWeight = FontWeight.Bold)
            Text(receta.nombre, fontSize = 18.sp, fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Kcal: ${receta.calorias}")
            Text("Tip: ${receta.recomendacion}")

            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = onCocinar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("COCINAR")
            }
        }
    }
}
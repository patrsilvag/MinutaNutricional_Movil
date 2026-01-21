package com.example.minutanutricional.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.LocalDining
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

    val iconoDia = when (receta.dia) {
        "Lunes" -> Icons.Outlined.CalendarToday
        "Martes" -> Icons.Outlined.Restaurant
        "Miércoles" -> Icons.Outlined.LocalDining
        "Jueves" -> Icons.Outlined.Restaurant
        else -> Icons.AutoMirrored.Outlined.MenuBook
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), // 🔒 altura consistente
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.secondary
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(iconoDia, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = receta.dia,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }


            Text(
                text = receta.nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = receta.recomendacion ?: "Sin recomendación disponible",
                fontSize = 13.sp,
                maxLines = 2
            )

            // 👇 control visual
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onCocinar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("COCINAR")
            }
        }

    }
}

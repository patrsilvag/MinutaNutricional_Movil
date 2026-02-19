package com.example.minutanutricional.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minutanutricional.model.Receta
import com.example.minutanutricional.components.ComboBoxDia
import com.example.minutanutricional.components.TarjetaReceta

import com.example.minutanutricional.data.RecetaRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

// --------------------
// MENÚ (GRILLA) + FILTRO
// --------------------
@Composable
fun PantallaMinuta(
    onLogout: () -> Unit,
    onVerDetalle: (Receta) -> Unit
) {
    var recetas by remember { mutableStateOf<List<Receta>>(emptyList()) }
    var diaSeleccionado by remember { mutableStateOf("Todos") }

    val dias = listOf("Todos", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes")

    // 🔥 Firebase Realtime Database
    LaunchedEffect(Unit) {
        RecetaRepository.escucharRecetas { lista ->
            recetas = lista
        }
    }

    val recetasFiltradas = remember(recetas, diaSeleccionado) {
        when (diaSeleccionado) {
            "Todos" -> recetas
            else -> recetas.filter { it.dia == diaSeleccionado }
        }
    }

    val totalRecetasMostradas = recetasFiltradas.size

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
            Text("Menú Semanal", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = onLogout) { Text("Salir") }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text("Toca una receta para ver detalles")
        Text("Mostrando $totalRecetasMostradas de ${recetas.size} recetas")

        Spacer(modifier = Modifier.height(10.dp))

        ComboBoxDia(
            opciones = dias,
            seleccionado = diaSeleccionado,
            onSeleccionar = { diaSeleccionado = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(recetasFiltradas) { receta ->
                TarjetaReceta(
                    receta = receta,
                    onCocinar = { onVerDetalle(receta) }
                )
            }
        }
    }
}
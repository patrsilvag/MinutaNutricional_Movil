package com.example.minutanutricional

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// --------------------
// MODELO
// --------------------
data class Receta(
    val dia: String,
    val nombre: String,
    val calorias: Int,
    val recomendacion: String
)

// --------------------
// ACTIVITY
// --------------------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

// --------------------
// NAVEGACIÓN SIMPLE
// --------------------
@Composable
fun AppNavigation() {
    var pantallaActual by remember { mutableStateOf("login") }
    var recetaSeleccionada by remember { mutableStateOf<Receta?>(null) }

    Surface(modifier = Modifier.fillMaxSize()) {
        when (pantallaActual) {
            "login" -> PantallaLogin(
                onLoginSuccess = { pantallaActual = "menu" },
                onIrARegistro = { pantallaActual = "registro" },
                onIrARecuperar = { pantallaActual = "recuperar" }
            )

            "registro" -> PantallaRegistro(onVolver = { pantallaActual = "login" })

            "recuperar" -> PantallaRecuperar(onVolver = { pantallaActual = "login" })

            "menu" -> PantallaMinuta(
                onLogout = { pantallaActual = "login" },
                onVerDetalle = { receta ->
                    recetaSeleccionada = receta
                    pantallaActual = "detalle"
                }
            )

            "detalle" -> {
                val r = recetaSeleccionada
                if (r == null) {
                    pantallaActual = "menu"
                } else {
                    PantallaDetalle(
                        receta = r,
                        onVolver = { pantallaActual = "menu" }
                    )
                }
            }
        }
    }
}

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

// --------------------
// RECUPERAR
// --------------------
@Composable
fun PantallaRecuperar(onVolver: () -> Unit) {
    var correo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Recuperar Clave", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onVolver, modifier = Modifier.fillMaxWidth()) {
            Text("ENVIAR CÓDIGO")
        }
    }
}

// --------------------
// COMBO BOX (DROPDOWN) PARA FILTRAR
// --------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboBoxDia(
    opciones: List<String>,
    seleccionado: String,
    onSeleccionar: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = seleccionado,
            onValueChange = {},
            readOnly = true,
            label = { Text("Filtrar por día") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(
                    type = MenuAnchorType.PrimaryNotEditable,
                    enabled = true
                )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        onSeleccionar(opcion)
                        expanded = false
                    }
                )
            }
        }
    }
}


// --------------------
// MENÚ (GRILLA) + FILTRO
// --------------------
@Composable
fun PantallaMinuta(
    onLogout: () -> Unit,
    onVerDetalle: (Receta) -> Unit
) {
    val recetas = listOf(
        Receta("Lunes", "Lentejas con Arroz", 450, "Rico en hierro."),
        Receta("Martes", "Pollo al Jugo", 350, "Usar poca sal."),
        Receta("Miércoles", "Charquicán", 300, "Plato completo."),
        Receta("Jueves", "Pescado al Horno", 280, "Fuente de Omega 3."),
        Receta("Viernes", "Fideos con Salsa", 500, "Cuidar porción.")
    )

    val dias = listOf("Todos", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes")
    var diaSeleccionado by remember { mutableStateOf("Todos") }

    val recetasFiltradas = remember(diaSeleccionado) {
        if (diaSeleccionado == "Todos") recetas
        else recetas.filter { it.dia == diaSeleccionado }
    }

    // Header + Combo fijo, grilla scrolleable
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
        Text("Toca una receta para ver detalles:")
        Spacer(modifier = Modifier.height(10.dp))

        ComboBoxDia(
            opciones = dias,
            seleccionado = diaSeleccionado,
            onSeleccionar = { diaSeleccionado = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 columnas en phone (legible)
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
        HorizontalDivider()
        FilaTabla("Recomendación", receta.recomendacion)
    }
}

@Composable
fun FilaTabla(columna1: String, columna2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = columna1,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Medium
        )
        Text(
            text = columna2,
            modifier = Modifier.weight(1f)
        )
    }
}

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

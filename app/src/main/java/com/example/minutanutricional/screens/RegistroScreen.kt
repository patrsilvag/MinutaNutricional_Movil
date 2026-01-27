package com.example.minutanutricional.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// Importa el componente si está en otra carpeta
import com.example.minutanutricional.components.CurvedBackground

@Composable
fun PantallaRegistro(onVolver: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var aceptaTerminos by remember { mutableStateOf(false) }
    var objetivoNutricional by remember { mutableStateOf("Mantener") }
    val objetivos = listOf("Bajar", "Mantener", "Subir")

    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorTerminos by remember { mutableStateOf<String?>(null) }
    var mensajeOk by remember { mutableStateOf<String?>(null) }


    // 1. Usamos un Box para poder poner el fondo detrás
    Box(modifier = Modifier.fillMaxSize()) {

        // 2. Llamamos al fondo curvo (se dibuja primero, queda al fondo)
        CurvedBackground()

        // 3. El contenido principal (Column) encima del fondo
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
                onValueChange = {
                    nombre = it
                    errorNombre = null
                    mensajeOk = null
                },
                label = { Text("Nombre Completo") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorNombre != null,
                supportingText = { errorNombre?.let { Text(it) } }
            )


            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = aceptaTerminos,
                    onCheckedChange = { aceptaTerminos = it }
                )
                Text("Acepto términos y condiciones")
            }
            errorTerminos?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Objetivo nutricional", fontWeight = FontWeight.SemiBold)

            objetivos.forEach { opcion ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (objetivoNutricional == opcion),
                        onClick = { objetivoNutricional = opcion }
                    )
                    Text(opcion)
                }
            }
            Text("Seleccionado: $objetivoNutricional")

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val n = nombre.trim()

                    errorNombre = when {
                        n.isBlank() -> "Ingrese su nombre"
                        n.length < 3 -> "Nombre muy corto"
                        else -> null
                    }

                    errorTerminos = when {
                        !aceptaTerminos -> "Debe aceptar términos y condiciones"
                        else -> null
                    }

                    if (errorNombre == null && errorTerminos == null) {
                        mensajeOk = "Registro válido (simulado)"
                        onVolver()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("GUARDAR Y VOLVER") }

        }
    }
}
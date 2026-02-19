package com.example.minutanutricional.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.minutanutricional.components.CurvedBackground
import com.example.minutanutricional.viewmodel.AuthViewModel

@Composable
fun PantallaRegistro(
    onVolver: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    // Estados para los campos (Agregamos email y password)
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var aceptaTerminos by remember { mutableStateOf(false) }
    var objetivoNutricional by remember { mutableStateOf("Mantener") }
    val objetivos = listOf("Bajar", "Mantener", "Subir")

    // Estados de validación
    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorEmail by remember { mutableStateOf<String?>(null) }
    var errorPassword by remember { mutableStateOf<String?>(null) }
    var errorTerminos by remember { mutableStateOf<String?>(null) }
    var errorFirebase by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        CurvedBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Registro de Usuario",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Campo Nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it; errorNombre = null },
                label = { Text("Nombre Completo") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorNombre != null
            )
            errorNombre?.let { Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }

            Spacer(modifier = Modifier.height(8.dp))

            // Campo Email (NUEVO)
            OutlinedTextField(
                value = email,
                onValueChange = { email = it; errorEmail = null },
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = errorEmail != null
            )
            errorEmail?.let { Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }

            Spacer(modifier = Modifier.height(8.dp))

            // Campo Contraseña (NUEVO)
            OutlinedTextField(
                value = password,
                onValueChange = { password = it; errorPassword = null },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = errorPassword != null
            )
            errorPassword?.let { Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }

            Spacer(modifier = Modifier.height(16.dp))

            // Selección de Objetivo
            Text("Objetivo nutricional", fontWeight = FontWeight.SemiBold, modifier = Modifier.align(Alignment.Start))
            objetivos.forEach { opcion ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    RadioButton(
                        selected = (objetivoNutricional == opcion),
                        onClick = { objetivoNutricional = opcion }
                    )
                    Text(opcion)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Términos y Condiciones
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = aceptaTerminos, onCheckedChange = { aceptaTerminos = it; errorTerminos = null })
                Text("Acepto los términos y condiciones", fontSize = 14.sp)
            }
            errorTerminos?.let { Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón de Acción
            Button(
                onClick = {
                    val n = nombre.trim()
                    val e = email.trim()
                    val p = password.trim()

                    // Validaciones
                    errorNombre = if (n.length < 3) "Nombre muy corto" else null
                    errorEmail = if (!android.util.Patterns.EMAIL_ADDRESS.matcher(e).matches()) "Email inválido" else null
                    errorPassword = if (p.length < 6) "Mínimo 6 caracteres" else null
                    errorTerminos = if (!aceptaTerminos) "Debe aceptar los términos" else null

                    if (errorNombre == null && errorEmail == null && errorPassword == null && errorTerminos == null) {
                        // Llamada real al ViewModel (Debe implementar la función registrar)
                        authViewModel.registrar(e, p,
                            onSuccess = { onVolver() },
                            onError = { msg -> errorFirebase = msg }
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                enabled = !authViewModel.isLoading
            ) {
                if (authViewModel.isLoading) CircularProgressIndicator(strokeWidth = 2.dp, modifier = Modifier.size(20.dp))
                else Text("CREAR CUENTA")
            }

            // Mostrar error de Firebase si existe
            errorFirebase?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(it, color = MaterialTheme.colorScheme.error, fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(15.dp))

            TextButton(onClick = onVolver) {
                Text("¿Ya tienes cuenta? Inicia sesión")
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}
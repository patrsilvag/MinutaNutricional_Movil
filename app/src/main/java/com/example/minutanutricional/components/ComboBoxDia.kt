package com.example.minutanutricional.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

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
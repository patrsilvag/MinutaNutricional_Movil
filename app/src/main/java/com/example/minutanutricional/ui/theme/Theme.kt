package com.example.minutanutricional.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = Color.White,

    secondary = GreenSoft,
    onSecondary = GrayText,


    background = BgLight,
    onBackground = GrayText,

    surface = Color.White,
    onSurface = GrayText,
)

@Composable
fun MinutaNutricionalTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}

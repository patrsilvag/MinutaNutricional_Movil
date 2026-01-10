package com.example.minutanutricional.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path

@Composable
fun CurvedBackground() {
    val color = MaterialTheme.colorScheme.primary

    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        /* ---------------- TOP CURVE (más pequeña y suave) ---------------- */
        val topBaseY = h * 0.16f      // antes ~0.22f
        val topCurveY = h * 0.20f     // antes ~0.30f

        val topPath = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, topBaseY)
            quadraticBezierTo(
                w / 2f, topCurveY,
                w, topBaseY
            )
            lineTo(w, 0f)
            close()
        }

        drawPath(path = topPath, color = color)

        /* ---------------- BOTTOM CURVE (más liviana) ---------------- */
        val bottomBaseY = h * 0.88f   // antes ~0.85f
        val bottomCurveY = h * 0.84f  // antes ~0.77f

        val bottomPath = Path().apply {
            moveTo(0f, h)
            lineTo(0f, bottomBaseY)
            quadraticBezierTo(
                w / 2f, bottomCurveY,
                w, bottomBaseY
            )
            lineTo(w, h)
            close()
        }

        drawPath(path = bottomPath, color = color)
    }
}

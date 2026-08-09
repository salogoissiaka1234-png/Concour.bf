package com.ragnard.concoursprobf.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val GreenPrimary = Color(0xFF1B7A3D)
val GreenDark = Color(0xFF0F4D26)
val AccentGold = Color(0xFFE0A800)
val BackgroundLight = Color(0xFFF7F9F7)
val SurfaceLight = Color(0xFFFFFFFF)

private val LightColors = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = Color.White,
    secondary = AccentGold,
    background = BackgroundLight,
    surface = SurfaceLight
)

private val DarkColors = darkColorScheme(
    primary = GreenPrimary,
    onPrimary = Color.White,
    secondary = AccentGold,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E)
)

@Composable
fun ConcoursProBFTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

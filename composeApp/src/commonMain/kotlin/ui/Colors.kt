package ui

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter

val lightColorPalette = lightColors(
    primary = Color.Blue,
    secondary = Color.Green,

    background = Color(244, 239, 244),
    onBackground = Color.Black,
    surface = Color(252, 252, 252),
    onSurface = Color.Black

)

val darkColorPalette = darkColors(
    primary = Color.LightGray,
    secondary = Color.DarkGray,

    background = Color(42, 46, 51),
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color(118,118,118)

)

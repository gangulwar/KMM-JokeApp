package ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
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

@Composable
private fun animateColor(targetValue: Color) =
    animateColorAsState(
        targetValue = targetValue,
        animationSpec = tween(durationMillis = 2000)
    ).value

@Composable
fun Colors.switch() = copy(
    primary = animateColor(primary),
    primaryVariant = animateColor(primaryVariant),
    secondary = animateColor(secondary),
    secondaryVariant = animateColor(secondaryVariant),
    background = animateColor(background),
    surface = animateColor(surface),
    error = animateColor(error),
    onPrimary = animateColor(onPrimary),
    onSecondary = animateColor(onSecondary),
    onBackground = animateColor(onBackground),
    onSurface = animateColor(onSurface),
    onError = animateColor(onError)
)
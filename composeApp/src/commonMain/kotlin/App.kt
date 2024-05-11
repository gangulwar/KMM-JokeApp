import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Shapes
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import jokeapp.composeapp.generated.resources.Res
import jokeapp.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.MainScope
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Resource
import org.jetbrains.compose.resources.painterResource
import presentation.MainScreen
import ui.darkColorPalette
import ui.lightColorPalette
import utils.robotoFontFamily
import viewmodel.ColorScheme
import viewmodel.ColorSchemeViewModel

import viewmodel.JokeViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val colorSchemeViewModel = ColorSchemeViewModel()
    val jokeViewModel = JokeViewModel()



    MaterialTheme {
        MainScreen(
            colorSchemeViewModel = colorSchemeViewModel,
            jokeViewModel = jokeViewModel
        )
    }
}
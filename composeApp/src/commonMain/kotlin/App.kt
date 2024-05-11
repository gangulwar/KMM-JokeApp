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
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Resource
import org.jetbrains.compose.resources.painterResource
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

    val colors = when (colorSchemeViewModel.colorScheme.value) {
        ColorScheme.Light -> lightColorPalette
        ColorScheme.Dark -> darkColorPalette
    }

    MaterialTheme {
        AppContent(
            colorSchemeViewModel = colorSchemeViewModel,
            jokeViewModel = jokeViewModel,
            colors = colors
        )
    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppContent(
    colorSchemeViewModel: ColorSchemeViewModel,
    jokeViewModel: JokeViewModel,
    colors: Colors
) {

    var jokeList = jokeViewModel.jokes.collectAsState()

    var showContent by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text(
                    "Compose: $greeting", style = TextStyle(
                        fontFamily = robotoFontFamily,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

        Text(
            text = jokeList.value.firstOrNull()?.toString() ?: ""
        )
    }
}
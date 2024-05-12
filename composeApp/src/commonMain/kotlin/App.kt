import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import presentation.MainScreen
import viewmodel.ColorSchemeViewModel
import viewmodel.JokeViewModel

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
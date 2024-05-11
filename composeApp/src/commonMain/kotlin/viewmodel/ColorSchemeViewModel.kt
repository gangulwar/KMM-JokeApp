package viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class ColorSchemeViewModel : ViewModel() {
    private val _colorScheme = mutableStateOf(ColorScheme.Light)
    val colorScheme: State<ColorScheme> = _colorScheme

    fun toggleColorScheme() {
        _colorScheme.value = if (_colorScheme.value == ColorScheme.Light) {
            ColorScheme.Dark
        } else {
            ColorScheme.Light
        }
    }
}

enum class ColorScheme {
    Light,
    Dark
}

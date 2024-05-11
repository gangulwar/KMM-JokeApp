package viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ColorSchemeViewModel : ViewModel() {
    private val _colorScheme = MutableStateFlow(ColorScheme.Light)
    val colorScheme: StateFlow<ColorScheme> = _colorScheme

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

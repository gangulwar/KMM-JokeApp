package utils

import androidx.compose.ui.text.font.FontFamily

expect class FontLoader() {
    fun loadRobotoFontFamily(): FontFamily
}

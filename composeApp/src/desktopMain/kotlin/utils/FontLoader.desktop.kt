package utils

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

actual class FontLoader actual constructor() {
    actual fun loadRobotoFontFamily(): FontFamily {
        return FontFamily(
            Font("font/roboto_bold.ttf", FontWeight.Bold),
            Font("font/roboto_medium.ttf", FontWeight.Medium),
            Font("font/roboto_regular.ttf", FontWeight.Normal)
        )
    }
}
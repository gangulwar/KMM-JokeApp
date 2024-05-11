package utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.gangulwar.jokeapp.R

actual class FontLoader actual constructor() {
    actual fun loadRobotoFontFamily(): FontFamily {
        return FontFamily(
            Font(R.font.roboto_bold, FontWeight.Bold),
            Font(R.font.roboto_medium, FontWeight.Medium),
            Font(R.font.roboto_regular, FontWeight.Normal)
        )
    }
}
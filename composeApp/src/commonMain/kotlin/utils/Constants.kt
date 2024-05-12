package utils

object Constants {

}

fun getCategoryEmoji(category: String): String {
    println("getCategoryEmoji() called with $category")
    return when (category) {
        "Programming" -> "👨‍💻"
        "Misc" -> "🤪"
        "Dark" -> "☠️"
        "Pun" -> "🤓"
        "Spooky" -> "👻"
        "Christmas" -> "🎅"
        else -> ""
    }
}

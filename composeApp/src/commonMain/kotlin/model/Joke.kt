package model

data class Joke(
    val category: String,
    val type: String,
    val setup: String?,
    val delivery: String?,
    val joke: String?,
    val flags: Flags,
    val safe: Boolean,
    val id: Int,
    val lang: String
)
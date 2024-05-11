package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Flags

@Serializable
data class Joke(
    val category: String,
    val type: String,
    @SerialName("joke") val content: String?,
    val setup: String? = null,
    val delivery: String? = null,
    val flags: Flags,
    val id: Int,
    val safe: Boolean,
    val lang: String
)
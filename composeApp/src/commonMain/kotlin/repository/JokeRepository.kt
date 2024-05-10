package repository

import client.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.util.InternalAPI
import kotlinx.coroutines.flow.flow


import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import model.Flags
import model.Joke

class JokeRepository {
    @OptIn(InternalAPI::class)
//    suspend fun getJokeList(): List<Joke>{
//
////        var response=httpClient.get(
////            "https://v2.jokeapi.dev/joke/Any?amount=10"
////        )
////
////        println(response.body<Any?>().toString())
////        return response.body()
//
//
//        val response = httpClient.get("https://v2.jokeapi.dev/joke/Any?amount=10")
//        println("Raw JSON response: ${response.bodyAsText()}") // Log raw JSON response
//
//        return Json.decodeFromString(string = response.bodyAsText())
//
//
//    }

//    suspend fun getJokeList(): List<Joke> {
//        val response = httpClient.get("https://v2.jokeapi.dev/joke/Any?amount=10")
//        val jsonResponse = response.bodyAsText()
//
//
//        val startIndex = jsonResponse.indexOf("\"jokes\":") + "\"jokes\":".length
//
//
//        val jokesJson = jsonResponse.substring(startIndex)
//
//
//        return Json.decodeFromString(jokesJson)
//    }

    suspend fun getJokeList(): List<Joke> {
        val response = httpClient.get("https://v2.jokeapi.dev/joke/Any?amount=10")
        val jsonResponse = response.bodyAsText()


//        val jokes = Json.decodeFromString<List<Joke>>(jsonResponse)
//
//
//        jokes.forEach { joke ->
//            println("Category: ${joke.category}")
//            println("Type: ${joke.type}")
//            println("Content: ${joke.content ?: joke.setup}")
//            joke.delivery?.let { println("Delivery: $it") }
//            println("Flags: ${joke.flags}")
//            println("ID: ${joke.id}")
//            println("Safe: ${joke.safe}")
//            println("Lang: ${joke.lang}")
//            println()
//        }

        return parseJoke(jsonResponse)
    }


    private fun parseJoke(jsonString: String): List<Joke> {
        val json = Json.parseToJsonElement(jsonString)
        val jokes = mutableListOf<Joke>()

        if (json is JsonObject) {
            val jokeArray = json["jokes"]?.jsonArray

            jokeArray?.forEach { jokeElement ->
                if (jokeElement is JsonObject) {
                    val category = jokeElement["category"]?.jsonPrimitive?.contentOrNull ?: ""
                    val type = jokeElement["type"]?.jsonPrimitive?.contentOrNull ?: ""
                    val content = jokeElement["joke"]?.jsonPrimitive?.contentOrNull
                    val setup = jokeElement["setup"]?.jsonPrimitive?.contentOrNull
                    val delivery = jokeElement["delivery"]?.jsonPrimitive?.contentOrNull
                    val flagsObject = jokeElement["flags"] as? JsonObject
                    val flags = parseFlags(flagsObject)
                    val id = jokeElement["id"]?.jsonPrimitive?.intOrNull ?: -1
                    val safe = jokeElement["safe"]?.jsonPrimitive?.booleanOrNull ?: false
                    val lang = jokeElement["lang"]?.jsonPrimitive?.contentOrNull ?: ""

                    val joke = Joke(category, type, content, setup, delivery, flags, id, safe, lang)
                    jokes.add(joke)
                }
            }
        }

        return jokes
    }

    private fun parseFlags(flagsObject: JsonObject?): Flags {
        val nsfw = flagsObject?.get("nsfw")?.jsonPrimitive?.booleanOrNull ?: false
        val religious = flagsObject?.get("religious")?.jsonPrimitive?.booleanOrNull ?: false
        val political = flagsObject?.get("political")?.jsonPrimitive?.booleanOrNull ?: false
        val racist = flagsObject?.get("racist")?.jsonPrimitive?.booleanOrNull ?: false
        val sexist = flagsObject?.get("sexist")?.jsonPrimitive?.booleanOrNull ?: false
        val explicit = flagsObject?.get("explicit")?.jsonPrimitive?.booleanOrNull ?: false
        return Flags(nsfw, religious, political, racist, sexist, explicit)
    }

    fun getJokes()= flow {
        emit(
            getJokeList()
        )
    }

//    suspend fun getSinglePartJoke():List<Joke>{
//
//    }
}

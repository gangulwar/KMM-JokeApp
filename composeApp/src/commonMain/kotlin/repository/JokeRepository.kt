package repository

import client.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow
import model.Joke

class JokeRepository {
    suspend fun getJokeList(): List<Joke>{
        var response=httpClient.get(
            "https://v2.jokeapi.dev/joke/Any?amount=10"
        )

        return response.body()
    }

    fun getJokes()= flow {
        emit(
            getJokeList()
        )
    }
}

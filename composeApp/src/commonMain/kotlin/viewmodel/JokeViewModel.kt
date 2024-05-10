package viewmodel

import androidx.compose.runtime.collectAsState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Joke
import repository.JokeRepository

class JokeViewModel : ViewModel() {

    private val _jokes = MutableStateFlow<List<Joke>>(listOf())

    val jokes = _jokes.asStateFlow()

    private val jokeRepository = JokeRepository()

    suspend fun makeApiRequestAndGetJokes(){
        viewModelScope.launch {
            jokeRepository.getJokes().collect { joke ->
                _jokes.update {
                    it + joke
                }
            }
        }
    }
}
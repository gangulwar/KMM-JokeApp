package viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Joke
import repository.JokeRepository
import utils.getCategoryEmoji

class JokeViewModel : ViewModel() {

    private val _jokes = MutableStateFlow<List<Joke>>(listOf())

    val jokes = _jokes.asStateFlow()

    private val jokeRepository = JokeRepository()

    val currentIndexs = mutableStateOf(CardIndexes())

    val currentCategoryEmoji = MutableStateFlow("Loading...")

    init {
        viewModelScope.launch(Dispatchers.Main) {
            makeApiRequestAndGetJokes()
        }
    }

    suspend fun makeApiRequestAndGetJokes() {
        viewModelScope.launch {
            jokeRepository.getJokes().collect { joke ->
                _jokes.update {
                    it + joke
                }
            }

            updateEmoji()
        }
    }

    fun nextIndexes() {
        currentIndexs.value = currentIndexs.value.copy(
            firstCardIndex = currentIndexs.value.firstCardIndex + 1,
            secondCardIndex = currentIndexs.value.secondCardIndex + 1,
            thirdCardIndex = currentIndexs.value.thirdCardIndex + 1

        )

        updateEmoji()

        if (currentIndexs.value.thirdCardIndex % 5 == 0) {
            viewModelScope.launch {
                makeApiRequestAndGetJokes()
            }
        }
    }

    fun previousIndexes() {

        if (currentIndexs.value.firstCardIndex != 0) {
            currentIndexs.value = currentIndexs.value.copy(
                firstCardIndex = currentIndexs.value.firstCardIndex - 1,
                secondCardIndex = currentIndexs.value.secondCardIndex - 1,
                thirdCardIndex = currentIndexs.value.thirdCardIndex - 1
            )
        }

        updateEmoji()

    }

    fun updateEmoji() {
        try {
            currentCategoryEmoji.value = getCategoryEmoji(
                _jokes.value[currentIndexs.value.firstCardIndex].category
            )
        } catch (e: IndexOutOfBoundsException) {
            currentCategoryEmoji.value = "⏳"
        }

    }
}

data class CardIndexes(
    var firstCardIndex: Int = 0,
    var secondCardIndex: Int = 1,
    var thirdCardIndex: Int = 2,
)
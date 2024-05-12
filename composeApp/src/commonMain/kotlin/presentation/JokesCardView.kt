package presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utils.robotoFontFamily
import viewmodel.JokeViewModel

@Composable
fun JokesCardView(
    colors: Colors,
    onSwipe: () -> Unit,
    jokeViewModel: JokeViewModel
) {
    val jokeList = jokeViewModel.jokes.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(23.dp),
        contentAlignment = Alignment.Center
    ) {

        if (jokeList.isNotEmpty()) {


            val thirdCardIndex = jokeViewModel.currentIndexs.value.thirdCardIndex
            val secondCardIndex = jokeViewModel.currentIndexs.value.secondCardIndex
            val firstCardIndex = jokeViewModel.currentIndexs.value.firstCardIndex

            val thirdCardJoke = if (thirdCardIndex < jokeList.size) {
                jokeList[thirdCardIndex]
            } else {
                null
            }

            val secondCardJoke = if (secondCardIndex < jokeList.size) {
                jokeList[secondCardIndex]
            } else {
                null
            }

            val firstCardJoke = if (firstCardIndex < jokeList.size) {
                jokeList[firstCardIndex]
            } else {
                null
            }

            if (thirdCardJoke != null) {
                JokeCard(
                    colors = colors,
                    joke = thirdCardJoke,
                    cardNumber = 3
                )
            }

            if (secondCardJoke != null) {
                JokeCard(
                    colors = colors,
                    joke = secondCardJoke,
                    cardNumber = 2
                )
            }

            if (firstCardJoke != null) {
                SwipeCard(
                    onSwipeLeft = {
                        onSwipe()
//                    jokeViewModel.previousIndexes()
//                    println("Left Swiped")
                    }, onSwipeRight = {
                        onSwipe()
//                    jokeViewModel.nextIndexes()
//                    println("Right Swiped")
                    }
                ) {

                    JokeCard(
                        colors =
                        colors,
                        joke = jokeList[jokeViewModel.currentIndexs.value.firstCardIndex],
                        cardNumber = 1
                    )

                }
            }else{
                Text(
                    text =
                    "Waiting for jokes...",
                    style = TextStyle(
                        fontFamily = robotoFontFamily,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        color = colors.onBackground
                    )
                )
            }
        } else {
            CircularProgressIndicator()
        }


    }
}
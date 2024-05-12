package presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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


            JokeCard(
                colors =
                colors,
                joke = jokeList[jokeViewModel.currentIndexs.value.thirdCardIndex],
                cardNumber = 3
            )

            JokeCard(
                colors =
                colors,
                joke = jokeList[jokeViewModel.currentIndexs.value.secondCardIndex],
                cardNumber = 2
            )

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
        } else {
            CircularProgressIndicator()
        }


    }
}
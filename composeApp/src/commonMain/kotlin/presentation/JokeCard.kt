package presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Joke
import utils.robotoFontFamily

@Composable
fun JokeCard(
    colors: Colors,
    joke: Joke,
    cardNumber: Int
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(
                when (cardNumber) {
                    1 -> 1f
                    2 -> 0.85f
                    3 -> 0.75f
                    else -> 1f
                }
            )
            .height(300.dp)
            .offset(
                y =
                when (cardNumber) {
                    1 -> 0.dp
                    2 -> 15.dp
                    3 -> 30.dp
                    else -> {
                        0.dp
                    }
                }

            )
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(23.dp),
                ambientColor = colors.onSurface,
                spotColor = colors.onSurface
            ),
        shape = RoundedCornerShape(23.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = colors.surface
        ),
        elevation = CardDefaults.elevatedCardElevation(

        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            if (joke.content == null) {
                Text(
                    text =
                    joke.setup.toString(),
                    style = TextStyle(
                        fontFamily = robotoFontFamily,
                        fontSize = processMessage(joke.setup.toString()),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        color = colors.onBackground
                    )
                )

                Text(
                    text =
                    joke.delivery.toString(), style = TextStyle(
                        fontFamily = robotoFontFamily,
                        fontSize = processMessage(joke.delivery.toString()),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = colors.onBackground
                    )
                )
            } else {
                Text(
                    text =
                    joke.content.toString(), style = TextStyle(
                        fontFamily = robotoFontFamily,
                        fontSize = processMessage(joke.content.toString()),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = colors.onBackground
                    )
                )
            }

        }
    }

}


fun processMessage(message: String): TextUnit {

//    println("---")
//    println("$message ${message.length}")
    return if (message.length >= 50) {
        15.sp
    } else {
        20.sp
    }
}
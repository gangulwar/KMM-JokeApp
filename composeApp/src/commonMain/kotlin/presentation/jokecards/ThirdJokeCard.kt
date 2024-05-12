package presentation.jokecards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Joke
import utils.robotoFontFamily

@Composable
fun ThirdJokeCard(
    colors: Colors,
    joke: Joke
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .height(300.dp)
            .offset(y = 30.dp)
//                        .padding(25.dp)
            .shadow(
                elevation = 10.dp,
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
        if (joke.content==null){
            Text(
                text =
                joke.setup.toString(),
                style = TextStyle(
                    fontFamily = robotoFontFamily,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = colors.onBackground
                )
            )

            Text(
                text =
                joke.delivery.toString(), style = TextStyle(
                    fontFamily = robotoFontFamily,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = colors.onBackground
                )
            )
        }else{
            Text(
                text =
                joke.content.toString(), style = TextStyle(
                    fontFamily = robotoFontFamily,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = colors.onBackground
                )
            )
        }
    }
}
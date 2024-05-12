package presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jokeapp.composeapp.generated.resources.Res
import jokeapp.composeapp.generated.resources.light_left_arrow
import jokeapp.composeapp.generated.resources.light_right_arrow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NavigationButtons(
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(23.dp),
        horizontalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    onLeftClick()
                },
            painter = painterResource(Res.drawable.light_left_arrow),
            contentDescription = "Left Arrow"
        )


        Image(
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    onRightClick()
                },
            painter = painterResource(Res.drawable.light_right_arrow),
            contentDescription = "Right Arrow"
        )
    }
}
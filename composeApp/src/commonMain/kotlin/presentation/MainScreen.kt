package presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import jokeapp.composeapp.generated.resources.Res
import jokeapp.composeapp.generated.resources.dark_share
import jokeapp.composeapp.generated.resources.download
import jokeapp.composeapp.generated.resources.light_left_arrow
import jokeapp.composeapp.generated.resources.light_right_arrow
import jokeapp.composeapp.generated.resources.light_share
import jokeapp.composeapp.generated.resources.moon
import jokeapp.composeapp.generated.resources.sun
import model.Joke
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.jokecards.FirstJokeCard
import presentation.jokecards.SecondJokeCard
import presentation.jokecards.ThirdJokeCard
import ui.darkColorPalette
import ui.lightColorPalette
import utils.getCategoryEmoji
import utils.robotoFontFamily
import viewmodel.ColorScheme
import viewmodel.ColorSchemeViewModel
import viewmodel.JokeViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun MainScreen(
    colorSchemeViewModel: ColorSchemeViewModel,
    jokeViewModel: JokeViewModel
) {

    val colorScheme by colorSchemeViewModel.colorScheme.collectAsState()
    val colors = when (colorScheme) {
        ColorScheme.Light -> lightColorPalette
        ColorScheme.Dark -> darkColorPalette
    }

    val jokeList = jokeViewModel.jokes.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Joke App", style = TextStyle(
                            fontFamily = robotoFontFamily,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = colors.onBackground
                        )
                    )
                },
                actions = {
                    Image(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clickable {
                                colorSchemeViewModel.toggleColorScheme()
                            },
                        painter = painterResource(
                            if (colors == lightColorPalette) {
                                Res.drawable.sun
                            } else {
                                Res.drawable.moon
                            }
                        ),
                        contentDescription = "Change Theme"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colors.background
                )
            )
        },
        containerColor = colors.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

                Text(
                    text = jokeViewModel.currentCategoryEmoji.value,
                    style = TextStyle(
                        fontFamily = robotoFontFamily,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Normal,
                        color = colors.onBackground
                    )
                )


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
                            jokeViewModel.previousIndexes()
                            println("Left Swiped")
                        }, onSwipeRight = {
                            jokeViewModel.nextIndexes()
                            println("Right Swiped")
                        }
                    ) {

                        JokeCard(
                            colors =
                            colors,
                            joke = jokeList[jokeViewModel.currentIndexs.value.firstCardIndex],
                            cardNumber = 1
                        )

                    }
                }
//                    ThirdJokeCard(
//                        colors = colors,
//                        joke = jokeList[2]
//                    )
//
//                    SecondJokeCard(
//                        colors = colors,
//                        joke = jokeList[1]
//                    )
//
//                    FirstJokeCard(
//                        colors = colors,
//                        joke = jokeList[0]
//                    )

                else {
                    CircularProgressIndicator()
                }


            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(23.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            jokeViewModel.previousIndexes()
                        },
                    painter = painterResource(Res.drawable.light_left_arrow),
                    contentDescription = "Left Arrow"
                )


                Image(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            jokeViewModel.nextIndexes()
                        },
                    painter = painterResource(Res.drawable.light_right_arrow),
                    contentDescription = "Right Arrow"
                )
            }
        }
    }

}



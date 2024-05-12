package presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jokeapp.composeapp.generated.resources.Res
import jokeapp.composeapp.generated.resources.light_left_arrow
import jokeapp.composeapp.generated.resources.light_right_arrow
import jokeapp.composeapp.generated.resources.moon
import jokeapp.composeapp.generated.resources.sun
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.darkColorPalette
import ui.lightColorPalette
import ui.switch
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
    val colorsA = when (colorScheme) {
        ColorScheme.Light -> lightColorPalette
        ColorScheme.Dark -> darkColorPalette
    }

    val colors = colorsA.switch()

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
                            if (colorsA == lightColorPalette) {
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
        containerColor = colors.background,
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

            JokesCardView(
                colors = colors,
                onSwipe = {
                    jokeViewModel.nextIndexes()
                },
                jokeViewModel = jokeViewModel
            )

            NavigationButtons(
                onLeftClick = {
                    jokeViewModel.previousIndexes()
                },
                onRightClick = {
                    jokeViewModel.nextIndexes()
                }
            )
        }
    }
}



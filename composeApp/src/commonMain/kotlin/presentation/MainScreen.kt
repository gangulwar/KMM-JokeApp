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
import jokeapp.composeapp.generated.resources.light_share
import jokeapp.composeapp.generated.resources.moon
import jokeapp.composeapp.generated.resources.sun
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.darkColorPalette
import ui.lightColorPalette
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
                "Dark", style = TextStyle(
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
                contentAlignment = Alignment.Center // Align content to the bottom
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
                ) {}

                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(300.dp)
                        .offset(y = 15.dp)
//                        .padding(15.dp)
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
                ) {}

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
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
//                        Box(
//                            modifier = Modifier.fillMaxHeight()
//                                .align(Alignment.End),
//                            contentAlignment = Alignment.BottomEnd
//                        ) {
//                            Image(
//                                painterResource(
//                                    if (colors == lightColorPalette) {
//                                        Res.drawable.light_share
//                                    } else {
//                                        Res.drawable.dark_share
//                                    }
//                                ),
//                                contentDescription = "Share"
//                            )
//                        }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    )
                    {
                        Text(
                            "Jokes about anti-vaxxer parents never get old.", style = TextStyle(
                                fontFamily = robotoFontFamily,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                color = colors.onBackground
                            )
                        )
                        Text(
                            "Just like their kids.", style = TextStyle(
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.light_share),
                    contentDescription = "Share"
                )
                Image(
                    painter = painterResource(Res.drawable.download),
                    contentDescription = "Share"
                )
            }

        }
    }

}

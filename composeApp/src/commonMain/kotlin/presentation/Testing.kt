package presentation

import androidx.compose.runtime.Composable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import androidx.compose.material3.Text

@Composable
fun SwipeableCard(
    modifier: Modifier = Modifier,
    onSwipe: () -> Unit,
    content: @Composable () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }

    Box(
        modifier = modifier
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .graphicsLayer {
                // Apply rotation based on the drag amount
                rotationY = offsetX * 0.1f
                // Apply scaling based on the drag amount
                scaleX = (1 - (offsetX.absoluteValue / 300f)).coerceIn(0.5f, 1f)
                scaleY = (1 - (offsetX.absoluteValue / 300f)).coerceIn(0.5f, 1f)
                // Apply alpha fading based on the drag amount
                alpha = (1 - (offsetX.absoluteValue / 300f)).coerceIn(0f, 1f)
            }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    offsetX += dragAmount.absoluteValue
                    if (offsetX.absoluteValue > 300.dp.value) {
                        onSwipe()
                    }
                }
                detectTapGestures {
                    if (offsetX.absoluteValue < 100.dp.value) {
                        // Not enough swipe, consider as tap
                        // You can handle tap action here
                    }
                }
            }
    ) {
        content()
    }
}




@Composable
fun TinderLikeSwipe() {
    val profiles = listOf("Profile 1", "Profile 2", "Profile 3") // Add your profile data here
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SwipeableCard(
            modifier = Modifier.padding(16.dp),
            onSwipe = { currentIndex++ },
            content = {
                Text(text = profiles.getOrNull(currentIndex) ?: "No more profiles")
            }
        )
    }
}

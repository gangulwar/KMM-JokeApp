package presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun SwipeCard(
    onSwipeLeft: () -> Unit = {},
    onSwipeRight: () -> Unit = {},
    swipeThreshold: Float = 400f,
    sensitivityFactor: Float = 3f,
    content: @Composable () -> Unit
) {
    var offset by remember { mutableStateOf(0f) }
    var dismissRight by remember { mutableStateOf(false) }
    var dismissLeft by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density

    var isSwiping by remember { mutableStateOf(false) }

    LaunchedEffect(dismissRight) {
        if (dismissRight) {
            delay(150)
            onSwipeRight.invoke()
            dismissRight = false
            offset = 0f
        }
    }

    LaunchedEffect(dismissLeft) {
        if (dismissLeft) {
            delay(150)
            onSwipeLeft.invoke()
            dismissLeft = false
            offset = 0f
        }
    }

    Box(modifier = Modifier
        .offset { IntOffset(offset.roundToInt(), 0) }
        .pointerInput(Unit) {
            detectHorizontalDragGestures(

                onDragStart = {
                    isSwiping = true
                    dismissRight = false
                    dismissLeft = false
                },
                onDragEnd = {
                    offset = 0f
                    isSwiping = false

                }) { change, dragAmount ->
                if (!isSwiping) {
                    return@detectHorizontalDragGestures
                }
                offset += (dragAmount / density) * sensitivityFactor
                when {
                    offset > swipeThreshold -> {
                        isSwiping = false
                        dismissRight = true
//                        offset=0f
                    }

                    offset < -swipeThreshold -> {
                        isSwiping = false
                        dismissLeft = true
//                        offset=0f
                    }
                }
                if (change.positionChange() != Offset.Zero) change.consume()
            }
        }
        .graphicsLayer(
            alpha = if (dismissRight || dismissLeft) {
                0f
            } else if (!isSwiping){
                1f
            }else {
                val normalizedOffset = offset.coerceIn(-swipeThreshold, swipeThreshold) / swipeThreshold
                1f - normalizedOffset.absoluteValue
            },
            rotationZ = animateFloatAsState(offset / 50).value
        )

    ) {
        content()
    }
}



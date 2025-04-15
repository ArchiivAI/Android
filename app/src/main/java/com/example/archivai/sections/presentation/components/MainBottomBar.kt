package com.example.archivai.sections.presentation.components

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MainBottomBar(
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    fabSize: Dp = 52.dp,
    contentColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
) {
    val density = LocalDensity.current
    val animatedSelectedIndex by animateFloatAsState(
        targetValue = selectedIndex.toFloat(),
        animationSpec = tween(300)
    )

    LeftToRight {
        Column {
            BoxWithConstraints(
                modifier = modifier
                    .fillMaxWidth()
                    .height(fabSize * 1.5f)
                    .drawBehind {
                        val path = createCurvePath(
                            selectedIndex = animatedSelectedIndex,
                            itemWidth = size.width / items.size,
                            fabSize = fabSize,
                        )
                        drawPath(path = path, color = backgroundColor)
                    }
            ) {
                val fabX = remember(animatedSelectedIndex) {
                    with(density) {
                        ((maxWidth / items.size) * (animatedSelectedIndex + 0.5f)).toPx()
                    }
                }

                Box(
                    modifier = Modifier
                        .size(fabSize)
                        .graphicsLayer {
                            translationX = fabX - fabSize.toPx() / 2
                            translationY += 10.dp.toPx()
                        }
                        .background(backgroundColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(items[selectedIndex].iconRes),
                        contentDescription = "",
                        tint = contentColor,
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    items.forEachIndexed { index, item ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .weight(1f)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) { onItemSelected(index) }
                        ) {
                            AnimatedVisibility(
                                index != selectedIndex,
                                enter = slideInVertically { it },
                                exit = slideOutVertically { it }
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        painter = painterResource(item.iconRes),
                                        contentDescription = "",
                                        tint = contentColor
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = stringResource(id = item.labelRes),
                                        color = contentColor,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Box(
                Modifier
                    .background(backgroundColor)
                    .fillMaxWidth()
                    .padding(WindowInsets.navigationBars.asPaddingValues())
                    .padding(bottom = 8.dp)
            )
        }
    }
}

private fun DrawScope.createCurvePath(
    selectedIndex: Float,
    itemWidth: Float,
    fabSize: Dp,
): Path {
    val totalWidth = size.width
    val totalHeight = size.height

    val fabSizePx = fabSize.toPx()
    val curveBottomOffsetPx = 8.dp.toPx()
    val fabTopOffsetPx = 8.dp.toPx()

    val fabRadius = fabSizePx / 2
    val fabMargin = totalHeight - fabSizePx - fabTopOffsetPx - curveBottomOffsetPx
    val curveHalfWidth = fabRadius * 2 + fabMargin

    val centerX = (itemWidth * selectedIndex) + (itemWidth / 2f)
    val bottomNavY = totalHeight - fabSizePx

    val firstCurveStart = Offset(
        x = centerX - curveHalfWidth,
        y = bottomNavY
    )

    val firstCurveEnd = Offset(
        x = centerX,
        y = totalHeight - curveBottomOffsetPx
    )

    val topControlX = fabRadius + fabRadius / 2
    val topControlY = bottomNavY + fabRadius / 6
    val bottomControlX = fabRadius + fabRadius / 2
    val bottomControlY = fabRadius / 4

    return Path().apply {
        moveTo(0f, bottomNavY)
        lineTo(firstCurveStart.x, firstCurveStart.y)

        cubicTo(
            x1 = firstCurveStart.x + topControlX,
            y1 = topControlY,
            x2 = firstCurveEnd.x - bottomControlX,
            y2 = firstCurveEnd.y - bottomControlY,
            x3 = firstCurveEnd.x,
            y3 = firstCurveEnd.y
        )

        cubicTo(
            x1 = firstCurveEnd.x + bottomControlX,
            y1 = firstCurveEnd.y - bottomControlY,
            x2 = centerX + curveHalfWidth - topControlX,
            y2 = topControlY,
            x3 = centerX + curveHalfWidth,
            y3 = bottomNavY
        )

        lineTo(totalWidth, bottomNavY)
        lineTo(totalWidth, totalHeight)
        lineTo(0f, totalHeight)
        close()
    }
}



@Composable
fun LeftToRight(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr, content)
}



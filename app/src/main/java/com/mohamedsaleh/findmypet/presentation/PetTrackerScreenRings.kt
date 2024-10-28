package com.mohamedsaleh.findmypet.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mohamedsaleh.findmypet.R
import com.mohamedsaleh.findmypet.ui.theme.AppThemeColor

@Composable
fun PetTrackerRings(circleCount: Int, maxRadius: Float) {

	val infiniteTransition = rememberInfiniteTransition(label = "")

	val scale = infiniteTransition.animateFloat(
		initialValue = 0f,
		targetValue = 1f,
		animationSpec = infiniteRepeatable(
			animation = tween(durationMillis = 2000, easing = LinearEasing),
			repeatMode = RepeatMode.Restart,
		), label = ""
	)

	Box(
		contentAlignment = Alignment.Center,
		modifier = Modifier.size(200.dp)
	) {
		for (i in 0 until circleCount) {
			val individualScale = scale.value + (i * 0.2f)
			Canvas(
				modifier = Modifier
					.size((maxRadius * individualScale).dp)
			) {
				drawCircle(
					color = AppThemeColor,
					radius = size.minDimension / 2,
					style = Stroke(width = 3.dp.toPx())
				)
			}
		}

		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier.size(100.dp)
		) {
			Circle(
				radius = 70.dp,
				color = AppThemeColor.copy(alpha = 0.3f)
			)
			Image(
				painter = painterResource(id = R.drawable.dog),
				contentDescription = "dog",
				modifier = Modifier
					.size(100.dp)
					.clip(CircleShape)
					.align(Alignment.Center)
			)
		}

	}

}

@Composable
fun Circle(radius: Dp, color: Color) {
	Box(
		modifier = Modifier
			.size(radius * 2)
			.graphicsLayer { scaleX = 1f; scaleY = 1f }
			.background(color = color, shape = CircleShape)
	)
}
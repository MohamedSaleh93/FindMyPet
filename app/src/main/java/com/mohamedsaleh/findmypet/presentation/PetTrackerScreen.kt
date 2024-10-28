package com.mohamedsaleh.findmypet.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedsaleh.findmypet.R
import com.mohamedsaleh.findmypet.ui.theme.AppThemeColor
import com.mohamedsaleh.findmypet.ui.theme.FindMyPetTheme
import com.mohamedsaleh.findmypet.ui.theme.HeaderTextColor
import com.mohamedsaleh.findmypet.ui.theme.TrackerDescriptionColor
import com.mohamedsaleh.findmypet.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun PetTrackerScreen(
	modifier: Modifier = Modifier,
	petName: String,
	onClose: () -> Unit,
) {

	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(White),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Header(petName = petName, onClose)

		AnimatedRings(distance = 30)

		Spacer(modifier = Modifier.height(24.dp))

		Text(
			text = stringResource(id = R.string.tracker_page_description, petName),
			color = TrackerDescriptionColor,
			fontSize = 14.sp,
			modifier = Modifier.padding(horizontal = 16.dp),
			textAlign = TextAlign.Center,
		)
	}
}

@Composable
fun Header(
	petName: String,
	onClose: () -> Unit,
) {
	Surface(
		modifier = Modifier.fillMaxWidth(),
		color = White,
		shadowElevation = 4.dp
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(15.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically,
		) {
			Column {
				Text(
					text = petName,
					color = HeaderTextColor,
					fontWeight = FontWeight.Bold,
					fontSize = 28.sp,
				)

				Text(
					text = stringResource(id = R.string.pet_near_you),
					color = HeaderTextColor,
					fontWeight = FontWeight.Bold,
					fontSize = 20.sp,
				)
			}

			Column(
				modifier = Modifier.clickable {
					onClose()
				},
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Icon(
					painter = painterResource(id = R.drawable.ic_close),
					contentDescription = stringResource(id = R.string.close),
					modifier = Modifier
						.size(24.dp),
					tint = HeaderTextColor,
				)

				Text(
					text = stringResource(id = R.string.close),
					color = HeaderTextColor,
				)
			}
		}
	}
}

@Composable
fun AnimatedRings(distance: Int) {
	val outerRadius = remember {
		Animatable(150f)
	}
	val scope = rememberCoroutineScope()

	LaunchedEffect(distance) {
		val targetRadius = when {
			distance <= 5 -> 300f
			distance <= 15 -> 200f
			else -> 150f
		}

		scope.launch {
			outerRadius.animateTo(
				targetValue = targetRadius,
				animationSpec = tween(durationMillis = 500)
			)
		}
	}

	Box(
		contentAlignment = Alignment.Center
	) {
		for (i in 0..3) {
			val radius = outerRadius.value - (i * 30)
			Circle(radius = radius.dp, color = AppThemeColor.copy(alpha = 0.4f - i * 0.1f))
		}

		Image(
			painter = painterResource(id = R.drawable.dog),
			contentDescription = "dog",
			modifier = Modifier
				.size(100.dp)
				.clip(CircleShape)
		)
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

@Preview
@Composable
fun PetTrackerScannerPreview() {
	FindMyPetTheme {
		Scaffold {innerPadding ->
			PetTrackerScreen(modifier = Modifier.padding(innerPadding),petName = "Hatshi", {})
		}

	}
}
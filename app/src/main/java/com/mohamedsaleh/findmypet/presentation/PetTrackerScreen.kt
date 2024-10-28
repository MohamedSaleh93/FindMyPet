package com.mohamedsaleh.findmypet.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedsaleh.findmypet.R
import com.mohamedsaleh.findmypet.ui.theme.BackgroundColor
import com.mohamedsaleh.findmypet.ui.theme.FindMyPetTheme
import com.mohamedsaleh.findmypet.ui.theme.TrackerDescriptionColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun PetTrackerScreen(
	distanceFlow: Flow<Int>,
	petName: String,
	onClose: () -> Unit,
) {

	val currentDistance = remember { mutableIntStateOf(0) }

	LaunchedEffect(distanceFlow) {
		distanceFlow.collect {distance ->
			currentDistance.intValue = distance
		}
	}

	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(BackgroundColor),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		PetTrackerScreenHeader(petName = petName, onClose)

		PetTrackerRings(
			circleCount = calculateCircleCount(currentDistance.intValue),
			maxRadius = calculateMaxRadius(currentDistance.intValue))

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

private fun calculateCircleCount(distance: Int): Int = when {
	distance < 5 -> 5
	distance < 10 -> 4
	distance < 20 -> 3
	else -> 2
}

private fun calculateMaxRadius(distance: Int): Float = when {
	distance < 5 -> 250f
	distance < 10 -> 200f
	distance < 20 -> 150f
	else -> 100f
}

@Preview
@Composable
fun PetTrackerScannerPreview() {
	FindMyPetTheme {
		PetTrackerScreen(flowOf(30), petName = "Hatshi", {})
	}
}
package com.mohamedsaleh.findmypet.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedsaleh.findmypet.R
import com.mohamedsaleh.findmypet.ui.theme.HeaderTextColor
import com.mohamedsaleh.findmypet.ui.theme.White

@Composable
fun PetTrackerScreenHeader(
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
				.padding(15.dp)
				.padding(top = 10.dp),
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
package com.mohamedsaleh.findmypet.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.mohamedsaleh.findmypet.ui.theme.FindMyPetTheme

class PetTrackerActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			FindMyPetTheme {
				Scaffold {innerPadding ->
					PetTrackerScreen(modifier = Modifier.padding(innerPadding),petName = "Hatshi", {})
				}
			}
		}
	}
}
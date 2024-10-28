package com.mohamedsaleh.findmypet.presentation

import androidx.lifecycle.ViewModel
import com.mohamedsaleh.findmypet.domain.IScanPetTrackerUseCase

class PetTrackerViewModel(
	private val scanTrackerUseCase: IScanPetTrackerUseCase,
): ViewModel() {
}
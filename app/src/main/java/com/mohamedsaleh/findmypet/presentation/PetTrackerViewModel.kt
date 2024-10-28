package com.mohamedsaleh.findmypet.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedsaleh.findmypet.domain.IScanPetTrackerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * View model class for pet tracker screen
 * Communicate with use case to listen to the change of distance between the device and pet tracker
 * @param scanPetTrackerUseCase the use case class the scan for pet tracker and return flow of distances
 */
class PetTrackerViewModel(
	private val scanPetTrackerUseCase: IScanPetTrackerUseCase,
): ViewModel() {

	private val _distance = MutableStateFlow(30)
	val distance: StateFlow<Int> = _distance

	init {
		viewModelScope.launch {
			scanPetTrackerUseCase().collect {value ->
				_distance.value = value
			}
		}
	}
}
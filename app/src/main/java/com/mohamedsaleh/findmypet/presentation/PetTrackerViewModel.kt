package com.mohamedsaleh.findmypet.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedsaleh.findmypet.domain.IScanPetTrackerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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
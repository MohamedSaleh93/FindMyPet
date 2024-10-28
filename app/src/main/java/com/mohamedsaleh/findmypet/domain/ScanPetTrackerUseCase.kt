package com.mohamedsaleh.findmypet.domain

import com.mohamedsaleh.findmypet.data.IPetTrackerRepository
import kotlinx.coroutines.flow.Flow

class ScanPetTrackerUseCase(
	private val petTrackerRepository: IPetTrackerRepository,
): IScanPetTrackerUseCase {
	override fun invoke(): Flow<Int> {
		return petTrackerRepository.scanPetTracker()
	}
}
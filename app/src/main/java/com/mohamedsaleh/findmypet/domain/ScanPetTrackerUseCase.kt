package com.mohamedsaleh.findmypet.domain

import com.mohamedsaleh.findmypet.data.IPetTrackerRepository
import kotlinx.coroutines.flow.Flow

/**
 * This use case responsible for communicate with data layer and scan for pet tracker
 * and return flow of integers that tells the distance between the phone and the pet tracker
 * @param petTrackerRepository the repository class to scan the pet tracker from different data sources
 */
class ScanPetTrackerUseCase(
	private val petTrackerRepository: IPetTrackerRepository,
): IScanPetTrackerUseCase {
	override fun invoke(): Flow<Int> {
		return petTrackerRepository.scanPetTracker()
	}
}
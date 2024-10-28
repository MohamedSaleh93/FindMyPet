package com.mohamedsaleh.findmypet.data

import com.mohamedsaleh.findmypet.data.local.IPetTrackerScannerDataSource
import kotlinx.coroutines.flow.Flow

class PetTrackerRepository(
	private val petTrackerScannerLocalDataSource: IPetTrackerScannerDataSource,
): IPetTrackerRepository {
	override fun scanPetTracker(): Flow<Int> {
		return petTrackerScannerLocalDataSource.scanPetTracker()
	}
}
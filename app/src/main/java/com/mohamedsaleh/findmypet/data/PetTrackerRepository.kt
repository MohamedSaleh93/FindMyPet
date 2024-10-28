package com.mohamedsaleh.findmypet.data

import com.mohamedsaleh.findmypet.data.local.IPetTrackerScannerDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Repository pattern class that communicate with different data sources for pet trackers
 * It can be extended to use the remote data source if we need to get pet tracker by GPS from server
 * @param petTrackerScannerLocalDataSource the local data source of the pet tracker
 */
class PetTrackerRepository(
	private val petTrackerScannerLocalDataSource: IPetTrackerScannerDataSource,
): IPetTrackerRepository {
	override fun scanPetTracker(): Flow<Int> {
		return petTrackerScannerLocalDataSource.scanPetTracker()
	}
}
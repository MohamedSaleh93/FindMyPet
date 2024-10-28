package com.mohamedsaleh.findmypet.data.local

import com.mohamedsaleh.findmypet.data.local.IPetTrackerScannerDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Responsible for getting the distance between the device and pet tracker from local source
 * @param petTrackerScanner the track scanner class the return the distance
 */
class PetTrackerScannerLocalDataSource(
	private val petTrackerScanner: IPetTrackerScanner,
): IPetTrackerScannerDataSource {
	/**
	 * scan pet tracker
	 * @return flow of integers that tells the distance between the phone and the pet tracker
	 */
	override fun scanPetTracker(): Flow<Int> {
		return petTrackerScanner.scanPetTracker()
	}
}
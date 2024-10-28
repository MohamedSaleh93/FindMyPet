package com.mohamedsaleh.findmypet.data.local

import com.mohamedsaleh.findmypet.data.local.IPetTrackerScannerDataSource
import kotlinx.coroutines.flow.Flow

class PetTrackerScannerLocalDataSource(
	private val petTrackerScanner: IPetTrackerScanner,
): IPetTrackerScannerDataSource {
	override fun scanPetTracker(): Flow<Int> {
		return petTrackerScanner.scanPetTracker()
	}
}
package com.mohamedsaleh.findmypet.data.local

import kotlinx.coroutines.flow.Flow

interface IPetTrackerScannerDataSource {

	fun scanPetTracker(): Flow<Int>
}
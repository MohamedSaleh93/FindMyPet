package com.mohamedsaleh.findmypet.data.local

import com.mohamedsaleh.findmypet.data.local.IPetTrackerScanner
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class PetTrackerScanner: IPetTrackerScanner {
	override fun scanPetTracker(): Flow<Int> {
		return flow {
			while (true) {
				emit(Random.nextInt(0, 30))
				delay(2000)
			}
		}
	}
}
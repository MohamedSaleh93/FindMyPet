package com.mohamedsaleh.findmypet.data.local

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

/**
 * The main purpose of the Scanner class is to implement IPetTrackerScanner interface
 * to return the bluetooth device distance, but for now I mock the distance by random number
 */
class PetTrackerScanner: IPetTrackerScanner {
	/**
	 * scan for pet tracker and return the distance
	 * [it should be done by bluetooth later]
	 * @return flow of integers that tells the distance between the phone and pet tracker
	 */
	override fun scanPetTracker(): Flow<Int> {
		return flow {
			while (true) {
				emit(Random.nextInt(0, 30))
				delay(2000)
			}
		}
	}
}
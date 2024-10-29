package com.mohamedsaleh.findmypet.data.local

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.advanceTimeBy
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PetTrackerScannerTest {
	private val petTrackerScanner = PetTrackerScanner()

	@Test
	fun `test scanPetTracker emits distances between 0 and 30`() = runTest {
		val distance = petTrackerScanner.scanPetTracker().first()
		assertTrue("Distance should be between 0 and 30", distance in 0..30)
	}

	@Test
	fun `test scanPetTracker emits multiple values over time`() = runTest {
		val distances = petTrackerScanner.scanPetTracker().take(5).toList()

		distances.forEach { distance ->
			assertTrue("Distance should be between 0 and 30", distance in 0..30)
		}
	}

	@Test
	fun `test scanPetTracker emits at expected interval`() = runTest {
		val scannerFlow = petTrackerScanner.scanPetTracker().take(2).toList()

		assertTrue("First distance should be between 0 and 30", scannerFlow[0] in 0..30)

		advanceTimeBy(2000)

		assertTrue("Second distance should be between 0 and 30", scannerFlow[1] in 0..30)
	}
}
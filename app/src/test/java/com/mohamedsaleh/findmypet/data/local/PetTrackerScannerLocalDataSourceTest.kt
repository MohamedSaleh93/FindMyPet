package com.mohamedsaleh.findmypet.data.local

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PetTrackerScannerLocalDataSourceTest {

	private val petTrackerScanner: IPetTrackerScanner = mockk()
	private val dataSource = PetTrackerScannerLocalDataSource(petTrackerScanner)

	@Test
	fun `test scanPetTracker call petTrackerScanner and emits expected values`() = runTest {
		val expectedDistances = listOf(10, 15, 20)
		every { petTrackerScanner.scanPetTracker() } returns flowOf(10, 15, 20)

		val actualDistances = dataSource.scanPetTracker().toList()

		verify { petTrackerScanner.scanPetTracker() }
		assertEquals("The emitted distances should match the expected values", expectedDistances, actualDistances)
	}
}
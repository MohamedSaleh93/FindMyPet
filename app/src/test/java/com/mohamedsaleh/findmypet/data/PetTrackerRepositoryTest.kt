package com.mohamedsaleh.findmypet.data

import com.mohamedsaleh.findmypet.data.local.IPetTrackerScannerDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PetTrackerRepositoryTest {

	private val petTrackerScannerLocalDataSource: IPetTrackerScannerDataSource = mockk()
	private val repository = PetTrackerRepository(petTrackerScannerLocalDataSource)

	@Test
	fun `test scanPetTracker delegates to local data source and emits expected values`() = runTest {
		val expectedDistances = listOf(5, 10, 15)
		every { petTrackerScannerLocalDataSource.scanPetTracker() } returns flowOf(5, 10, 15)

		val actualDistances = repository.scanPetTracker().toList()

		verify { petTrackerScannerLocalDataSource.scanPetTracker() }
		assertEquals("The emitted distances should match the expected values", expectedDistances, actualDistances)
	}
}
package com.mohamedsaleh.findmypet.domain

import com.mohamedsaleh.findmypet.data.IPetTrackerRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ScanPetTrackerUseCaseTest {

	private val petTrackerRepository: IPetTrackerRepository = mockk()
	private val scanPetTrackerUseCase = ScanPetTrackerUseCase(petTrackerRepository)

	@Test
	fun `test invoke calls repository and emits expected values`() = runTest {
		val expectedDistances = listOf(8, 12, 20)
		every { petTrackerRepository.scanPetTracker() } returns flowOf(8, 12, 20)

		val actualDistances = scanPetTrackerUseCase().toList()

		verify { petTrackerRepository.scanPetTracker() }
		assertEquals("The emitted distances should match the expected values", expectedDistances, actualDistances)
	}
}
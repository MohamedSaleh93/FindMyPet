package com.mohamedsaleh.findmypet.presentation

import com.mohamedsaleh.findmypet.domain.IScanPetTrackerUseCase
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PetTrackerViewModelTest {

	@get:Rule
	val instantExecutorRule = InstantTaskExecutorRule()

	private val scanPetTrackerUseCase: IScanPetTrackerUseCase = mockk()
	private lateinit var viewModel: PetTrackerViewModel

	private val testDispatcher = StandardTestDispatcher()
	private val testScope = TestScope(testDispatcher)

	@Before
	fun setup() {
		Dispatchers.setMain(testDispatcher)
	}

	@After
	fun tearDown() {
		Dispatchers.resetMain()
	}

	@Test
	fun `test distance is updated from use case emissions`() = testScope.runTest {
		coEvery { scanPetTrackerUseCase.invoke() } returns flowOf(10, 15, 20)
		viewModel = PetTrackerViewModel(scanPetTrackerUseCase)
		advanceUntilIdle()

		assertEquals("The distance should match the last emitted value", 20, viewModel.distance.value)
	}
}
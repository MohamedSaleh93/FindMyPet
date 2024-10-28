package com.mohamedsaleh.findmypet.di

import com.mohamedsaleh.findmypet.data.IPetTrackerRepository
import com.mohamedsaleh.findmypet.data.PetTrackerRepository
import com.mohamedsaleh.findmypet.data.local.IPetTrackerScanner
import com.mohamedsaleh.findmypet.data.local.IPetTrackerScannerDataSource
import com.mohamedsaleh.findmypet.data.local.PetTrackerScanner
import com.mohamedsaleh.findmypet.data.local.PetTrackerScannerLocalDataSource
import com.mohamedsaleh.findmypet.domain.IScanPetTrackerUseCase
import com.mohamedsaleh.findmypet.domain.ScanPetTrackerUseCase
import com.mohamedsaleh.findmypet.presentation.PetTrackerViewModel

/**
 * A service locator object that provide dependencies required for the app.
 * This object is responsible for creating and managing the instance of different dependencies
 *
 * Instead of relying on dependency injection framework, this object use lazy initialization
 * to create instances only when they are first needed. And once it is created, the same instance
 * is reused for subsequent requests, ensuring singleton pattern for each dependency
 */
object DependenciesProvider {

	private var petTrackerScanner: IPetTrackerScanner? = null
	private var petTrackerDataSource: IPetTrackerScannerDataSource? = null
	private var petTrackerRepository: IPetTrackerRepository? = null
	private var scanPetTrackerUseCase: IScanPetTrackerUseCase? = null
	private var petTrackerViewModel: PetTrackerViewModel? = null

	/**
	 * Provide instance of `IPetTrackerScanner`
	 * If instance already exists, it returns the instance.
	 * Otherwise it creates new instance of `PetTrackerScanner`
	 *
	 * @return An instance of `PetTrackerScanner`
	 */
	fun providePetTrackerScanner(): IPetTrackerScanner =
		petTrackerScanner ?: PetTrackerScanner().also { petTrackerScanner = it }

	/**
	 * Provide instance of `IPetTrackerScannerDataSource`
	 * If instance already exists, it returns the instance.
	 * Otherwise it creates new instance of `PetTrackerScannerLocalDataSource`
	 *
	 * @return An instance of `PetTrackerScannerLocalDataSource`
	 */
	fun providePetTrackerScannerLocalDataSource(): IPetTrackerScannerDataSource =
		petTrackerDataSource ?: PetTrackerScannerLocalDataSource(
			petTrackerScanner = providePetTrackerScanner()
		).also { petTrackerDataSource = it }

	/**
	 * Provide instance of `IPetTrackerRepository`
	 * If instance already exists, it returns the instance.
	 * Otherwise it creates new instance of `PetTrackerRepository`
	 *
	 * @return An instance of `PetTrackerRepository`
	 */
	fun providePetTrackerRepository(): IPetTrackerRepository =
		petTrackerRepository ?: PetTrackerRepository(
			petTrackerScannerLocalDataSource = providePetTrackerScannerLocalDataSource()
		).also { petTrackerRepository = it }

	/**
	 * Provide instance of `IScanPetTrackerUseCase`
	 * If instance already exists, it returns the instance.
	 * Otherwise it creates new instance of `ScanPetTrackerUseCase`
	 *
	 * @return An instance of `ScanPetTrackerUseCase`
	 */
	fun provideScanPetTrackerUseCase(): IScanPetTrackerUseCase =
		scanPetTrackerUseCase ?: ScanPetTrackerUseCase(
			petTrackerRepository = providePetTrackerRepository()
		).also { scanPetTrackerUseCase = it }

	/**
	 * Provide instance of `PetTrackerViewModel`
	 * If instance already exists, it returns the instance.
	 * Otherwise it creates new instance of `PetTrackerViewModel`
	 *
	 * @return An instance of `PetTrackerViewModel`
	 */
	fun providePetTrackerViewModel(): PetTrackerViewModel =
		petTrackerViewModel ?: PetTrackerViewModel(
			scanPetTrackerUseCase = provideScanPetTrackerUseCase()
		).also { petTrackerViewModel = it }
}
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

object DependenciesProvider {

	private var petTrackerScanner: IPetTrackerScanner? = null
	private var petTrackerDataSource: IPetTrackerScannerDataSource? = null
	private var petTrackerRepository: IPetTrackerRepository? = null
	private var scanPetTrackerUseCase: IScanPetTrackerUseCase? = null
	private var petTrackerViewModel: PetTrackerViewModel? = null

	fun providePetTrackerScanner(): IPetTrackerScanner =
		petTrackerScanner ?: PetTrackerScanner()

	fun providePetTrackerScannerLocalDataSource(): IPetTrackerScannerDataSource =
		petTrackerDataSource ?: PetTrackerScannerLocalDataSource(
			petTrackerScanner = providePetTrackerScanner()
		)

	fun providePetTrackerRepository(): IPetTrackerRepository =
		petTrackerRepository ?: PetTrackerRepository(
			petTrackerScannerLocalDataSource = providePetTrackerScannerLocalDataSource()
		)

	fun provideScanPetTrackerUseCase(): IScanPetTrackerUseCase =
		scanPetTrackerUseCase ?: ScanPetTrackerUseCase(
			petTrackerRepository = providePetTrackerRepository()
		)

	fun providePetTrackerViewModel(): PetTrackerViewModel =
		petTrackerViewModel ?: PetTrackerViewModel(
			scanPetTrackerUseCase = provideScanPetTrackerUseCase()
		)
}
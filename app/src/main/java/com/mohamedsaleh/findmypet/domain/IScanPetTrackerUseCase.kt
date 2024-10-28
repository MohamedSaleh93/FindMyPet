package com.mohamedsaleh.findmypet.domain

import kotlinx.coroutines.flow.Flow

interface IScanPetTrackerUseCase {

	operator fun invoke(): Flow<Int>
}
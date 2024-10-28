package com.mohamedsaleh.findmypet.data

import kotlinx.coroutines.flow.Flow

interface IPetTrackerRepository {

	fun scanPetTracker(): Flow<Int>
}
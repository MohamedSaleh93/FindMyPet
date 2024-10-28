package com.mohamedsaleh.findmypet.data.local

import kotlinx.coroutines.flow.Flow

interface IPetTrackerScanner {

	fun scanPetTracker(): Flow<Int>
}
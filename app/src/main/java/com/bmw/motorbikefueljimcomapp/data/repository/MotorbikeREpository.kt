package com.bmw.motorbikefueljimcomapp.data.repository

import com.bmw.motorbikefueljimcomapp.data.Dao.MotorbikeDao
import com.bmw.motorbikefueljimcomapp.data.entities.MotorbikeEntity
import kotlinx.coroutines.flow.Flow

class MotorbikeREpository(private val motorbikeDao: MotorbikeDao) {
    fun getMotorbikesByOwner(ownerId: Int): Flow<List<MotorbikeEntity>> {
        return motorbikeDao.getMotorbikesByOwner(ownerId)
    }

    suspend fun insertMotorbike(motorbike: MotorbikeEntity): Long {
        return motorbikeDao.insertMotorbike(motorbike)
    }

    suspend fun updateMotorbike(motorbike: MotorbikeEntity) {
        motorbikeDao.updateMotorbike(motorbike)
    }

    suspend fun getMotorbikeByRegNumber(regNumber: String): MotorbikeEntity? {
        return motorbikeDao.getMotorbikeByRegNumber(regNumber)
    }

    suspend fun updateMotorbikeStatus(motorbikeId: Int, status: String) {
        motorbikeDao.updateMotorbikeStatus(motorbikeId, status)
    }
}
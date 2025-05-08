package com.bmw.motorbikefueljimcomapp.data.repository





import com.bmw.motorbikefueljimcomapp.data.MotorbikeDao
import com.bmw.motorbikefueljimcomapp.data.entities.MotorbikeEntity

class MotorbikeRoomRepository(private val motorbikeDao: MotorbikeDao) : MotorbikeREpository {
    override suspend fun insertMotorbike(motorbike: MotorbikeEntity): Long {
        return motorbikeDao.insertMotorbike(motorbike)
    }

    override suspend fun getAllMotorbikes(): List<MotorbikeEntity> {
        return motorbikeDao.getAllMotorbikes()
    }

    override suspend fun getMotorbikeById(motorbikeId: Long): MotorbikeEntity? {
        return motorbikeDao.getMotorbikeById(motorbikeId)
    }

    override suspend fun getMotorbikesByOwnerId(ownerId: String): List<MotorbikeEntity> {
        return motorbikeDao.getMotorbikesByOwnerId(ownerId)
    }
}
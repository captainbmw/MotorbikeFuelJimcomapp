package com.bmw.motorbikefueljimcomapp.data.repository



import com.bmw.motorbikefueljimcomapp.data.entities.MotorbikeEntity

interface MotorbikeREpository {
    suspend fun insertMotorbike(motorbike: MotorbikeEntity): Long
    suspend fun getAllMotorbikes(): List<MotorbikeEntity>
    suspend fun getMotorbikeById(motorbikeId: Long): MotorbikeEntity?
    suspend fun getMotorbikesByOwnerId(ownerId: String): List<MotorbikeEntity>
}
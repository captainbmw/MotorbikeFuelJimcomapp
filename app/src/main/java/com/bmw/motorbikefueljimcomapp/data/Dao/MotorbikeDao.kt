package com.bmw.motorbikefueljimcomapp.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bmw.motorbikefueljimcomapp.data.entities.MotorbikeEntity
import kotlinx.coroutines.flow.Flow


// MotorbikeDao.kt
@Dao
interface MotorbikeDao {
    @Insert
    suspend fun insertMotorbike(motorbike: MotorbikeEntity): Long

    @Update
    suspend fun updateMotorbike(motorbike: MotorbikeEntity)

    @Query("SELECT * FROM motorbikes WHERE ownerId = :ownerId AND status = 'Active'")
    fun getMotorbikesByOwner(ownerId: Int): Flow<List<MotorbikeEntity>>

    @Query("SELECT * FROM motorbikes WHERE regNumber = :regNumber")
    suspend fun getMotorbikeByRegNumber(regNumber: String): MotorbikeEntity?

    @Query("UPDATE motorbikes SET status = :status WHERE id = :motorbikeId")
    suspend fun updateMotorbikeStatus(motorbikeId: Int, status: String)
}

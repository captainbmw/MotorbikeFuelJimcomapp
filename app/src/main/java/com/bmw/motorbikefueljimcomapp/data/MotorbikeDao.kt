package com.bmw.motorbikefueljimcomapp.data



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bmw.motorbikefueljimcomapp.data.entities.MotorbikeEntity

@Dao
interface MotorbikeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMotorbike(motorbike: MotorbikeEntity): Long

    @Query("SELECT * FROM motorbikes")
    suspend fun getAllMotorbikes(): List<MotorbikeEntity>

    @Query("SELECT * FROM motorbikes WHERE id = :motorbikeId")
    suspend fun getMotorbikeById(motorbikeId: Long): MotorbikeEntity?

    @Query("SELECT * FROM motorbikes WHERE ownerId = :ownerId")
    suspend fun getMotorbikesByOwnerId(ownerId: String): List<MotorbikeEntity>
}
package com.bmw.motorbikefueljimcomapp.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bmw.motorbikefueljimcomapp.data.entities.DevotionEntity
import kotlinx.coroutines.flow.Flow

// DevotionDao.kt
@Dao
interface DevotionDao {
    @Insert
    suspend fun insertDevotion(devotion: DevotionEntity)

    @Query("SELECT * FROM devotions ORDER BY date DESC")
    fun getAllDevotions(): Flow<List<DevotionEntity>>

    @Query("SELECT * FROM devotions WHERE date = :date")
    suspend fun getDevotionByDate(date: String): DevotionEntity?
}
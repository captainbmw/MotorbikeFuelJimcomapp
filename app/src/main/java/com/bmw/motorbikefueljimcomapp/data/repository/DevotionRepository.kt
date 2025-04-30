package com.bmw.motorbikefueljimcomapp.data.repository

import com.bmw.motorbikefueljimcomapp.data.Dao.DevotionDao
import com.bmw.motorbikefueljimcomapp.data.entities.DevotionEntity
import kotlinx.coroutines.flow.Flow

class DevotionRepository(private val devotionDao: DevotionDao) {
    val allDevotions: Flow<List<DevotionEntity>> = devotionDao.getAllDevotions()

    suspend fun insertDevotion(devotion: DevotionEntity) {
        devotionDao.insertDevotion(devotion)
    }

    suspend fun getDevotionByDate(date: String): DevotionEntity? {
        return devotionDao.getDevotionByDate(date)
    }
}
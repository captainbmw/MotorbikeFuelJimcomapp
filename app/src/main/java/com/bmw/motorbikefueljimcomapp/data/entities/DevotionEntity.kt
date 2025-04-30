package com.bmw.motorbikefueljimcomapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// DevotionEntity.kt
@Entity(tableName = "devotions")
data class DevotionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val title: String,
    val scripture: String,
    val content: String,
    val prayerPoints: String,
    val author: String? = null,
    val isFeatured: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

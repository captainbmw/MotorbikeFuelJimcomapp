package com.bmw.motorbikefueljimcomapp.data



import androidx.room.Database
import androidx.room.RoomDatabase
import com.bmw.motorbikefueljimcomapp.data.entities.MotorbikeEntity

@Database(entities = [MotorbikeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun motorbikeDao(): MotorbikeDao
}
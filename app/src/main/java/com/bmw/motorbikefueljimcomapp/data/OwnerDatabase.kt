package com.bmw.motorbikefueljimcomapp.data

import com.bmw.motorbikefueljimcomapp.data.Dao.OwnerDao
import com.bmw.motorbikefueljimcomapp.data.entities.OwnerEntity



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [OwnerEntity::class], version = 1)
abstract class OwnerDatabase : RoomDatabase() {

    abstract fun ownerDao(): OwnerDao

    companion object {
        @Volatile
        private var INSTANCE: OwnerDatabase? = null

        fun getDatabase(context: Context): OwnerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OwnerDatabase::class.java,
                    "owner_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
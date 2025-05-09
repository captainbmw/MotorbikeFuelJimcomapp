package com.bmw.motorbikefueljimcomapp.data.Dao

import com.bmw.motorbikefueljimcomapp.data.entities.OwnerEntity



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface OwnerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOwner(owner: Owner)

    @Update
    suspend fun updateOwner(owner: com.bmw.motorbikefueljimcomapp.data.OwnerEntity)

    @Query("SELECT * FROM owners WHERE idNumber = :idNumber")
    suspend fun getOwnerByIdNumber(idNumber: String): OwnerEntity?

    @Query("UPDATE owners SET status = :status WHERE id = :ownerId")
    suspend fun updateOwnerStatus(ownerId: Int, status: String)

    @Query("SELECT * FROM owners WHERE status = 'active'")
    suspend fun getAllActiveOwners(): List<OwnerEntity>

    @Query("SELECT * FROM owners")
    suspend fun getAllOwners(): List<OwnerEntity>
}
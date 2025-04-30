package com.bmw.motorbikefueljimcomapp.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bmw.motorbikefueljimcomapp.data.entities.OwnerEntity
import com.bmw.motorbikefueljimcomapp.model.Owner
import kotlinx.coroutines.flow.Flow

@Dao
interface OwnerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOwner(owner: OwnerEntity)

    @Update
    suspend fun updateOwner(owner: OwnerEntity)

    @Query("SELECT * FROM owners WHERE id = :ownerId")
    suspend fun getOwner(ownerId: String): Owner?

    @Query("SELECT * FROM owners WHERE status = 'ACTIVE'")
    fun getActiveOwners(): Flow<List<Owner>>
    fun getOwnerByIdNumber(string: String): OwnerEntity?
    fun updateOwnerStatus(i: Int, string: String)
    fun getAllActiveOwners(): Flow<List<OwnerEntity>>
}
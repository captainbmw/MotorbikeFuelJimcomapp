package com.bmw.motorbikefueljimcomapp.data.repository

import com.bmw.motorbikefueljimcomapp.data.Dao.OwnerDao
import com.bmw.motorbikefueljimcomapp.data.entities.OwnerEntity
import kotlinx.coroutines.flow.Flow

class OwnerRepository(private val ownerDao: OwnerDao) {
    val allActiveOwners: Flow<List<OwnerEntity>> = ownerDao.getAllActiveOwners()

    suspend fun insertOwner(owner: OwnerEntity) {
        return ownerDao.insertOwner(owner)
    }

    suspend fun updateOwner(owner: OwnerEntity) {
        ownerDao.updateOwner(owner)
    }

    suspend fun getOwnerByIdNumber(idNumber: String): OwnerEntity? {
        return ownerDao.getOwnerByIdNumber(idNumber)
    }

    suspend fun updateOwnerStatus(ownerId: Int, status: String) {
        ownerDao.updateOwnerStatus(ownerId, status)
    }
}
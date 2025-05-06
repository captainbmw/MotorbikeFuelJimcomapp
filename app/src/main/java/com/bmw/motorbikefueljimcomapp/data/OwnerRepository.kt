package com.bmw.motorbikefueljimcomapp.data


import android.content.Context
import com.bmw.motorbikefueljimcomapp.data.Dao.OwnerDao
import com.bmw.motorbikefueljimcomapp.data.entities.OwnerEntity

import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

class OwnerRepository(private val context: Context) {

    private val ownerDao: OwnerDao = OwnerDatabase.getDatabase(context).ownerDao()

    val allActiveOwners: Flow<List<com.bmw.motorbikefueljimcomapp.data.Owner>> = flow {
        val owners = ownerDao.getAllActiveOwners()
        emit(owners.map { ownerEntity -> ownerEntity.toOwner() })
    }.flowOn(Dispatchers.IO)

    suspend fun insertOwner(owner: OwnerEntity) {
        ownerDao.insertOwner(owner.toOwner())
    }

    suspend fun updateOwner(owner: Owner) {
        ownerDao.updateOwner(owner.toOwnerEntity())
    }

    suspend fun getOwnerByIdNumber(idNumber: String): Owner? {
        val ownerEntity = ownerDao.getOwnerByIdNumber(idNumber)
        return ownerEntity?.toOwner()
    }

    suspend fun updateOwnerStatus(ownerId: Int, status: String) {
        ownerDao.updateOwnerStatus(ownerId, status)
    }

    suspend fun getAllOwners(): List<Owner> {
        val ownersEntities = ownerDao.getAllOwners()
        return ownersEntities.map { it.toOwner() }
    }
}



package com.bmw.motorbikefueljimcomapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bmw.motorbikefueljimcomapp.data.entities.OwnerEntity
import com.bmw.motorbikefueljimcomapp.data.repository.OwnerRepository
import kotlinx.coroutines.launch

class OwnerViewModel(private val repository: OwnerRepository) : ViewModel() {
    val allOwners: LiveData<List<OwnerEntity>> = repository.allActiveOwners.asLiveData()

    fun insertOwner(owner: OwnerEntity) = viewModelScope.launch {
        repository.insertOwner(owner)
    }

    fun updateOwner(owner: OwnerEntity) = viewModelScope.launch {
        repository.updateOwner(owner)
    }

    suspend fun getOwnerByIdNumber(idNumber: String): OwnerEntity? {
        return repository.getOwnerByIdNumber(idNumber)
    }

    fun updateOwnerStatus(ownerId: Int, status: String) = viewModelScope.launch {
        repository.updateOwnerStatus(ownerId, status)
    }
}
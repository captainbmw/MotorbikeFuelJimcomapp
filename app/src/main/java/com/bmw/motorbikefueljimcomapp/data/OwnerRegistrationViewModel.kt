package com.bmw.motorbikefueljimcomapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmw.motorbikefueljimcomapp.data.entities.OwnerEntity
import com.bmw.motorbikefueljimcomapp.data.repository.OwnerRepository
import com.bmw.motorbikefueljimcomapp.model.OperationStatus
import kotlinx.coroutines.launch

class OwnerRegistrationViewModel(private val ownerRepository: OwnerRepository) : ViewModel() {
    private val _registrationStatus = MutableLiveData<OperationStatus?>()
    val registrationStatus: LiveData<OperationStatus?> = _registrationStatus

    fun registerOwner(owner: OwnerEntity, onSuccess: () -> Unit) {
        _registrationStatus.value = OperationStatus.Loading
        viewModelScope.launch {
            try {
                ownerRepository.insertOwner(owner)
                _registrationStatus.value = OperationStatus.Success("Owner registered successfully")
                onSuccess()
            } catch (e: Exception) {
                _registrationStatus.value = OperationStatus.Error(e.message ?: "Failed to register owner")
            }
        }
    }
}
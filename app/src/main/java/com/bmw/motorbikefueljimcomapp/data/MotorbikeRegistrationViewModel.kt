package com.bmw.motorbikefueljimcomapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmw.motorbikefueljimcomapp.data.entities.MotorbikeEntity
import com.bmw.motorbikefueljimcomapp.data.repository.MotorbikeREpository
import com.bmw.motorbikefueljimcomapp.model.OperationStatus

import kotlinx.coroutines.launch

class MotorbikeRegistrationViewModel(private val motorbikeRepository: MotorbikeREpository) : ViewModel() {
    private val _registrationStatus = MutableLiveData<OperationStatus?>()
    val registrationStatus: LiveData<OperationStatus?> = _registrationStatus

    fun registerMotorbike(motorbike: MotorbikeEntity, onSuccess: (Long) -> Unit) {
        _registrationStatus.value = OperationStatus.Loading
        viewModelScope.launch {
            try {
                val newMotorbikeId = motorbikeRepository.insertMotorbike(motorbike)
                _registrationStatus.value = OperationStatus.Success("Motorbike registered successfully")
                onSuccess(newMotorbikeId)
            } catch (e: Exception) {
                _registrationStatus.value = OperationStatus.Error(e.message ?: "Failed to register motorbike")
            }
        }
    }
}
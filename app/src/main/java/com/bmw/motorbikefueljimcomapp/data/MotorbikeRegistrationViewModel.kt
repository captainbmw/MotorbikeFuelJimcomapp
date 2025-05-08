package com.bmw.motorbikefueljimcomapp.data



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmw.motorbikefueljimcomapp.data.entities.MotorbikeEntity
import com.bmw.motorbikefueljimcomapp.data.repository.MotorbikeREpository
import com.bmw.motorbikefueljimcomapp.model.OperationStatus
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MotorbikeRegistrationViewModel(
    private val motorbikeRepository: MotorbikeREpository,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _registrationStatus = MutableLiveData<OperationStatus?>()
    val registrationStatus: LiveData<OperationStatus?> = _registrationStatus

    private val _motorbikes = MutableLiveData<List<MotorbikeEntity>>()
    val motorbikes: LiveData<List<MotorbikeEntity>> = _motorbikes

    fun registerMotorbike(motorbike: MotorbikeEntity, onSuccess: (Long) -> Unit) {
        _registrationStatus.value = OperationStatus.Loading
        viewModelScope.launch {
            try {
                // Get the current user's UID
                val currentUser = auth.currentUser
                if (currentUser != null) {
                    val ownerId = currentUser.uid
                    // Create a new MotorbikeEntity with the ownerId
                    val newMotorbike = motorbike.copy(ownerId = ownerId)
                    // Insert the new MotorbikeEntity
                    val newMotorbikeId = motorbikeRepository.insertMotorbike(newMotorbike)
                    _registrationStatus.value = OperationStatus.Success("Motorbike registered successfully")
                    onSuccess(newMotorbikeId)
                } else {
                    _registrationStatus.value = OperationStatus.Error("User not authenticated")
                }
            } catch (e: Exception) {
                _registrationStatus.value = OperationStatus.Error(e.message ?: "Failed to register motorbike")
            }
        }
    }

    fun getMotorbikesByOwner() {
        _registrationStatus.value = OperationStatus.Loading
        viewModelScope.launch {
            try {
                val currentUser = auth.currentUser
                if (currentUser != null) {
                    val ownerId = currentUser.uid
                    val ownerMotorbikes = motorbikeRepository.getMotorbikesByOwnerId(ownerId)
                    _motorbikes.value = ownerMotorbikes
                    _registrationStatus.value = OperationStatus.Success("")
                } else {
                    _registrationStatus.value = OperationStatus.Error("User not authenticated")
                }
            } catch (e: Exception) {
                _registrationStatus.value = OperationStatus.Error(e.message ?: "Failed to retrieve motorbikes")
            }
        }
    }
}
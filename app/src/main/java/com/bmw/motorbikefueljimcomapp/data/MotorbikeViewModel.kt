package com.bmw.motorbikefueljimcomapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmw.motorbikefueljimcomapp.data.entities.MotorbikeEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MotorbikeViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val motorbikesRef = database.getReference("motorbikes")

    private val _motorbikesByOwner = MutableLiveData<List<MotorbikeEntity>>()
    val motorbikesByOwner: LiveData<List<MotorbikeEntity>> = _motorbikesByOwner

    private val _operationStatus = MutableLiveData<OperationStatus>()
    val operationStatus: LiveData<OperationStatus> = _operationStatus

    fun getMotorbikesByOwner(ownerId: String) {
        motorbikesRef.orderByChild("ownerId").equalTo(ownerId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val motorbikes = mutableListOf<MotorbikeEntity>()
                    for (motorbikeSnapshot in snapshot.children) {
                        motorbikeSnapshot.getValue<MotorbikeEntity>()?.let { motorbikes.add(it) }
                    }
                    _motorbikesByOwner.value = motorbikes
                }

                override fun onCancelled(error: DatabaseError) {
                    _operationStatus.value = OperationStatus.Error(error.message)
                }
            })
    }

    fun insertMotorbike(motorbike: MotorbikeEntity) = viewModelScope.launch {
        try {
            // Generate a unique ID if not provided
            if (motorbike.id.isEmpty()) {
                motorbike.id = ((motorbikesRef.push().key ?: Int) as Int).toString()
            }

            motorbikesRef.child(motorbike.id.toString()).setValue(motorbike).await()
            _operationStatus.value = OperationStatus.Success("Motorbike registered successfully")
        } catch (e: Exception) {
            _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to register motorbike")
        }
    }

    fun updateMotorbike(motorbike: MotorbikeEntity) = viewModelScope.launch {
        try {
            motorbikesRef.child(motorbike.id.toString()).setValue(motorbike).await()
            _operationStatus.value = OperationStatus.Success("Motorbike updated successfully")
        } catch (e: Exception) {
            _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to update motorbike")
        }
    }

    suspend fun getMotorbikeByRegNumber(regNumber: String): MotorbikeEntity? {
        return try {
            val snapshot = motorbikesRef.orderByChild("regNumber").equalTo(regNumber)
                .limitToFirst(1)
                .get()
                .await()

            snapshot.children.firstOrNull()?.getValue<MotorbikeEntity>()
        } catch (e: Exception) {
            _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to fetch motorbike")
            null
        }
    }

    fun updateMotorbikeStatus(motorbikeId: String, status: String) = viewModelScope.launch {
        try {
            val updates = hashMapOf<String, Any>(
                "status" to status,
                "updatedAt" to System.currentTimeMillis()
            )
            motorbikesRef.child(motorbikeId).updateChildren(updates).await()
            _operationStatus.value = OperationStatus.Success("Status updated successfully")
        } catch (e: Exception) {
            _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to update status")
        }
    }
}

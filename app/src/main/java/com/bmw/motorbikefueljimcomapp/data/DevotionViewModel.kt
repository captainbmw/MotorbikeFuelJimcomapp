package com.bmw.motorbikefueljimcomapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmw.motorbikefueljimcomapp.data.entities.DevotionEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DevotionViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val devotionsRef = database.getReference("devotions")

    private val _allDevotions = MutableLiveData<List<DevotionEntity>>()
    val allDevotions: LiveData<List<DevotionEntity>> = _allDevotions

    private val _operationStatus = MutableLiveData<OperationStatus>()
    val operationStatus: LiveData<OperationStatus> = _operationStatus

    init {
        // Set up realtime listener for all devotions
        devotionsRef.orderByChild("date").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val devotions = mutableListOf<DevotionEntity>()
                for (devotionSnapshot in snapshot.children) {
                    devotionSnapshot.getValue<DevotionEntity>()?.let { devotions.add(it) }
                }
                _allDevotions.value = devotions
            }

            override fun onCancelled(error: DatabaseError) {
                _operationStatus.value = OperationStatus.Error(error.message)
            }
        })
    }

    fun insertDevotion(devotion: DevotionEntity) = viewModelScope.launch {
        try {
            // Use date as the key for easy lookup
            devotionsRef.child(devotion.date).setValue(devotion).await()
            _operationStatus.value = OperationStatus.Success("Devotion added successfully")
        } catch (e: Exception) {
            _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to add devotion")
        }
    }

    suspend fun getDevotionByDate(date: String): DevotionEntity? {
        return try {
            val snapshot = devotionsRef.child(date).get().await()
            snapshot.getValue<DevotionEntity>()
        } catch (e: Exception) {
            _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to fetch devotion")
            null
        }
    }
}
package com.bmw.motorbikefueljimcomapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmw.motorbikefueljimcomapp.model.LoanApplication
import com.bmw.motorbikefueljimcomapp.model.OperationStatus
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoanApplicationViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val loanApplicationsRef = database.getReference("loanApplications")

    private val _loanApplications = MutableLiveData<List<LoanApplication>>()
    val loanApplications: LiveData<List<LoanApplication>> = _loanApplications

    private val _operationStatus = MutableLiveData<OperationStatus>()
    val operationStatus: LiveData<OperationStatus> = _operationStatus

    fun getLoanApplications() {
        loanApplicationsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val applications = mutableListOf<LoanApplication>()
                for (applicationSnapshot in snapshot.children) {
                    applicationSnapshot.getValue<LoanApplication>()?.let { applications.add(it) }
                }
                _loanApplications.value = applications
            }

            override fun onCancelled(error: DatabaseError) {
                _operationStatus.value = OperationStatus.Error(error.message)
            }
        })
    }

    fun submitLoanApplication(application: LoanApplication, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val applicationRef = loanApplicationsRef.push()
                application.id = applicationRef.key ?: "" // Get the unique key
                applicationRef.setValue(application).await()
                _operationStatus.value = OperationStatus.Success("Loan application submitted successfully")
                onSuccess() // Notify the UI
            } catch (e: Exception) {
                _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to submit loan application")
            }
        }
    }

    fun updateLoanApplicationStatus(applicationId: String, status: String, notes: String? = null) =
        viewModelScope.launch {
            try {
                val updates = mutableMapOf<String, Any>("status" to status)
                if (notes != null) {
                    updates["notes"] = notes
                }
                loanApplicationsRef.child(applicationId).updateChildren(updates).await()
                _operationStatus.value = OperationStatus.Success("Loan application status updated")
            } catch (e: Exception) {
                _operationStatus.value =
                    OperationStatus.Error(e.message ?: "Failed to update loan application status")
            }
        }

    suspend fun getLoanApplicationById(applicationId: String): LoanApplication? {
        return try {
            val snapshot = loanApplicationsRef.child(applicationId).get().await()
            snapshot.getValue<LoanApplication>()
        } catch (e: Exception) {
            _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to fetch application")
            null
        }
    }

    // Additional functions as needed (e.g., fetching applications by owner)
}
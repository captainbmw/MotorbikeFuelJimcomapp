package com.bmw.motorbikefueljimcomapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmw.motorbikefueljimcomapp.data.entities.LoanEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoanViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val loansRef = database.getReference("loans")

    private val _allLoans = MutableLiveData<List<LoanEntity>>()
    val allLoans: LiveData<List<LoanEntity>> = _allLoans

    private val _loansByOwner = MutableLiveData<List<LoanEntity>>()
    val loansByOwner: LiveData<List<LoanEntity>> = _loansByOwner

    private val _operationStatus = MutableLiveData<OperationStatus>()
    val operationStatus: LiveData<OperationStatus> = _operationStatus

    init {
        // Set up realtime listener for all loans
        loansRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val loans = mutableListOf<LoanEntity>()
                for (loanSnapshot in snapshot.children) {
                    loanSnapshot.getValue<LoanEntity>()?.let { loans.add(it) }
                }
                _allLoans.value = loans
            }

            override fun onCancelled(error: DatabaseError) {
                _operationStatus.value = OperationStatus.Error(error.message)
            }
        })
    }

    fun getLoansByOwner(ownerId: String) {
        loansRef.orderByChild("ownerId").equalTo(ownerId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val loans = mutableListOf<LoanEntity>()
                    for (loanSnapshot in snapshot.children) {
                        loanSnapshot.getValue<LoanEntity>()?.let { loans.add(it) }
                    }
                    _loansByOwner.value = loans
                }

                override fun onCancelled(error: DatabaseError) {
                    _operationStatus.value = OperationStatus.Error(error.message)
                }
            })
    }

    fun insertLoan(loan: LoanEntity) = viewModelScope.launch {
        try {
            val loanRef = loansRef.push()
            loan.id = ((loanRef.key ?: Int) as Int).toString()
            loanRef.setValue(loan).await()
            _operationStatus.value = OperationStatus.Success("Loan added successfully")
        } catch (e: Exception) {
            _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to add loan")
        }
    }

    fun updateLoan(loan: LoanEntity) = viewModelScope.launch {
        try {
            loansRef.child(loan.id.toString()).setValue(loan).await()
            _operationStatus.value = OperationStatus.Success("Loan updated successfully")
        } catch (e: Exception) {
            _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to update loan")
        }
    }

    suspend fun getLoanByNumber(loanNumber: String): LoanEntity? {
        return try {
            val snapshot = loansRef.orderByChild("loanNumber").equalTo(loanNumber)
                .limitToFirst(1)
                .get()
                .await()

            snapshot.children.firstOrNull()?.getValue<LoanEntity>()
        } catch (e: Exception) {
            _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to fetch loan")
            null
        }
    }

    fun updateLoanStatus(loanId: String, status: String) = viewModelScope.launch {
        try {
            val updates = hashMapOf<String, Any>(
                "status" to status,
                "updatedAt" to System.currentTimeMillis()
            )
            loansRef.child(loanId).updateChildren(updates).await()
            _operationStatus.value = OperationStatus.Success("Status updated successfully")
        } catch (e: Exception) {
            _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to update status")
        }
    }

    fun calculateTotalPayable(principal: Double, interestRate: Double, serviceFee: Double): Double {
        return principal + (principal * interestRate / 100) + serviceFee
    }

    fun generateLoanNumber(): String {
        val timestamp = System.currentTimeMillis()
        return "JIM${timestamp.toString().takeLast(6)}"
    }
}

sealed class OperationStatus {
    data class Success(val message: String) : OperationStatus()
    data class Error(val message: String) : OperationStatus()
    object Loading : OperationStatus()
}
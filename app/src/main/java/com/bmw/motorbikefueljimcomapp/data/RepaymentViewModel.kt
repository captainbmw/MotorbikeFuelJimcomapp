package com.bmw.motorbikefueljimcomapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmw.motorbikefueljimcomapp.model.OperationStatus
import com.bmw.motorbikefueljimcomapp.model.Repayment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RepaymentViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val repaymentsRef = database.getReference("repayments")

    private val _repaymentsForLoan = MutableLiveData<List<Repayment>>()
    val repaymentsForLoan: LiveData<List<Repayment>> = _repaymentsForLoan

    private val _operationStatus = MutableLiveData<OperationStatus>()
    val operationStatus: LiveData<OperationStatus> = _operationStatus

    fun getRepaymentsForLoan(loanId: String) {
        repaymentsRef.orderByChild("loanId").equalTo(loanId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val repayments = mutableListOf<Repayment>()
                    for (repaymentSnapshot in snapshot.children) {
                        repaymentSnapshot.getValue<Repayment>()?.let { repayments.add(it) }
                    }
                    _repaymentsForLoan.value = repayments
                }

                override fun onCancelled(error: DatabaseError) {
                    _operationStatus.value = OperationStatus.Error(error.message)
                }
            })
    }

    fun recordRepayment(repayment: Repayment, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val repaymentRef = repaymentsRef.push()
                repayment.id = repaymentRef.key ?: ""
                repaymentRef.setValue(repayment).await()
                _operationStatus.value = OperationStatus.Success("Repayment recorded successfully")
                onSuccess()
            } catch (e: Exception) {
                _operationStatus.value = OperationStatus.Error(e.message ?: "Failed to record repayment")
            }
        }
    }
}
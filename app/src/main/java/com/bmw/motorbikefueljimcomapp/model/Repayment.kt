package com.bmw.motorbikefueljimcomapp.model

data class Repayment(
    var id: String = "",
    val loanId: String = "",
    val repaymentDate: Long = System.currentTimeMillis(),
    val amountPaid: Double = 0.0,
    val paymentMethod: String = "",
    val transactionId: String? = null,
    val recordedBy: String? = null // Optional: User who recorded the repayment
)
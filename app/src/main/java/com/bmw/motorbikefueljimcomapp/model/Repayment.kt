package com.bmw.motorbikefueljimcomapp.model

data class Repayment(
    var id: String = "",
    val loanId: String = "",
    val repaymentDate: String = "",
    val amountPaid: String = "",
    val paymentMethod: String = "",
    val transactionId: String = "",
     // Optional: User who recorded the repayment
)
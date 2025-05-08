package com.bmw.motorbikefueljimcomapp.model


data class LoanApplication(
    var id: String = "",
    var ownerId: String = "",
    var motorbikeId: String = "",
    var loanAmount: Double = 0.0,
    var interestRate: Double = 0.0,
    var applicationDate: Long = System.currentTimeMillis(),
    var status: String = "Pending", // "Pending", "Approved", "Rejected"
    var notes: String? = null,
    var loanPurpose: String? = null,
    var repaymentPlan: String? = null,
    var guarantor1Id: String? = null,
    var guarantor2Id: String? = null,
    var termsAndConditionsAccepted: Boolean = false,
    var supportingDocuments: List<String> = emptyList()
)
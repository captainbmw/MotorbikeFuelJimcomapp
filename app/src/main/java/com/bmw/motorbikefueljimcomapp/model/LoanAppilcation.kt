package com.bmw.motorbikefueljimcomapp.model

data class LoanApplication(
    var id: String = "",
    val ownerId: String = "",       // ID of the loan applicant (Owner)
    val motorbikeId: String = "",   // ID of the motorbike involved
    val loanAmount: Double = 0.0,
    val interestRate: Double = 0.0,
    val applicationDate: Long = System.currentTimeMillis(),
    val status: String = "Pending", // "Pending", "Approved", "Rejected"
    val notes: String? = null,       // For additional information or reasons for rejection
    val loanPurpose: String = "",
    val repaymentPlan: String = "", // e.g., "Weekly", "Monthly"
    val guarantor1Id: String? = null,
    val guarantor2Id: String? = null,
    val termsAndConditionsAccepted: Boolean = false,
    val supportingDocuments: List<String> = emptyList() // Store URLs of uploaded documents
)

sealed class OperationStatus {
    data class Success(val message: String) : OperationStatus()
    data class Error(val message: String) : OperationStatus()
    object Loading : OperationStatus()
}
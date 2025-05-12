package com.bmw.motorbikefueljimcomapp.model




import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class LoanApplication(
    var id: String = "", // Realtime Database key
    var applicantName: String = "",
    var applicantIdNumber: String = "",
    var loanAmount: String = "",
    var loanPurpose: String = "",
    var applicationDate: String = "",
    var status: String = "Pending", // e.g., "Pending", "Approved", "Rejected"
    var note: String? = null // Optional field for notes
)
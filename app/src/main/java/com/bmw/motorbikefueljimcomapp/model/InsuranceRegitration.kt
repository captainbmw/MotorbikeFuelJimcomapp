package com.bmw.motorbikefueljimcomapp.model


import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class InsuranceRegistration(
    var id: String = "", // Realtime Database key
    var insuranceCompany: String = "",
    var policyNumber: String = "",
    var startDate: String = "",
    var expiryDate: String = "",
    var coverageType: String = "",
    var premiumAmount: String = ""
)
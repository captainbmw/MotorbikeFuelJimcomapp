package com.bmw.motorbikefueljimcomapp.model



data class Motorbike(
    var id: String = "", // Firestore document ID
    var numberPlate: String = "",
    var brand: String = "",
    var model: String = "",
    var color: String = ""
)
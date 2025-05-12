package com.bmw.motorbikefueljimcomapp.data


import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.bmw.motorbikefueljimcomapp.model.InsuranceRegistration
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_HOME
import com.google.firebase.database.FirebaseDatabase

class InsuranceRegistrationViewModel(var navController: NavHostController, var context: Context) : ViewModel() {

    var database: FirebaseDatabase? = null

    init {
        database = FirebaseDatabase.getInstance()
    }

    fun saveInsuranceRegistration(
        insuranceCompany: String,
        policyNumber: String,
        startDate: String,
        expiryDate: String,
        coverageType: String,
        premiumAmount: String
    ) {
        // Check if any field is blank
        if (insuranceCompany.isBlank() || policyNumber.isBlank() || startDate.isBlank() ||
            expiryDate.isBlank() || coverageType.isBlank() || premiumAmount.isBlank()
        ) {
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val registrationId = database?.reference?.child("InsuranceRegistrations")?.push()?.key
        if (registrationId == null) {
            Toast.makeText(context, "Error generating registration ID", Toast.LENGTH_SHORT).show()
            return
        }

        val insuranceRegistration = InsuranceRegistration(
            registrationId,
            insuranceCompany,
            policyNumber,
            startDate,
            expiryDate,
            coverageType,
            premiumAmount
        )

        database?.reference?.child("InsuranceRegistrations")?.child(registrationId)
            ?.setValue(insuranceRegistration)
            ?.addOnSuccessListener {
                Toast.makeText(context, "Insurance Registration saved successfully", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_HOME) // Example navigation after successful save
            }
            ?.addOnFailureListener { e ->
                Toast.makeText(context, "Error saving insurance registration: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // You can add functions to retrieve, update, or delete registrations as needed.
    // For example, to retrieve:
    /*
    fun getInsuranceRegistrations(): MutableState<List<InsuranceRegistration>> {
        val registrationsListState = mutableStateOf<List<InsuranceRegistration>>(emptyList())

        database?.reference?.child("InsuranceRegistrations")?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val registrations = mutableListOf<InsuranceRegistration>()
                for (registrationSnapshot in snapshot.children) {
                    val registration = registrationSnapshot.getValue(InsuranceRegistration::class.java)
                    registration?.let { registrations.add(it) }
                }
                registrationsListState.value = registrations
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error fetching registrations: ${error.message}", Toast.LENGTH_SHORT).show()
                registrationsListState.value = emptyList() // Set to empty on failure
            }
        })

        return registrationsListState
    }
    */
}
package com.bmw.motorbikefueljimcomapp.data


import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.bmw.motorbikefueljimcomapp.model.Repayment
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOGIN
import com.google.firebase.database.FirebaseDatabase


class RepaymentViewModel(var navController: NavHostController, var context: Context) {
    var authRepository: OwnerRegistrationViewModel


    init {
        authRepository = OwnerRegistrationViewModel(navController, context)
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_LOGIN)
        }

    }


    fun makepayment(
        id: String, loanId: String, repaymentDate: String,
        amountPaid: String,
        paymentMethod:
        String,
        transactionId: String, trim: String, trim1: String, trim2: String
    ) {
        var id = System.currentTimeMillis().toString()
        var productData = Repayment(id, loanId, repaymentDate, amountPaid, paymentMethod, transactionId)
        var productRef = FirebaseDatabase.getInstance().getReference()
            .child("Payments/$id")

        productRef.setValue(productData).addOnCompleteListener {

            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}

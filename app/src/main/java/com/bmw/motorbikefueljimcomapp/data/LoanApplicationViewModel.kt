package com.bmw.motorbikefueljimcomapp.data


import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.bmw.motorbikefueljimcomapp.model.LoanApplication
import com.bmw.motorbikefueljimcomapp.model.Upload
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_HOME
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class LoanApplicationViewModel(var navController: NavHostController, var context: Context) {
    var authRepository: OwnerRegistrationViewModel


    init {
        authRepository = OwnerRegistrationViewModel(navController, context)
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_HOME)
        }

    }


    fun saveLoan(id: String,applicantName: String, applicantIdNumber: String,
                    loanAmount: String,
                    loanPurpose: String,
                    applicationDate: String,
                    status: String, note: String) {
        var id = System.currentTimeMillis().toString()
        var productData = LoanApplication(id, applicantName, applicantIdNumber, loanAmount, loanPurpose, applicationDate, status, note)
        var productRef = FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")

        productRef.setValue(productData).addOnCompleteListener {

            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun viewProducts(
        loan: MutableState<Upload>,
        Loans: SnapshotStateList<Upload>
    ): SnapshotStateList<Upload> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Loans")


        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Loans.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Upload::class.java)
                    loan.value = value!!
                    Loans.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return Loans
    }

    fun deleteProduct(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Loans/$id")

        delRef.removeValue().addOnCompleteListener {

            if (it.isSuccessful) {
                Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateProduct(id:String,
                      applicantName: String,
                      applicantIdNumber: String,
                      loanAmount: String,
                      loanPurpose: String,
                      applicationDate: String,
                      status: String, note: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Uploads/$id")

        var updateData = Upload(id, applicantName , applicantIdNumber , loanAmount, loanPurpose, applicationDate, status, note )
        updateRef.setValue(updateData).addOnCompleteListener {

            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
//
//    fun saveProductWithImage(id: String, applicantName: String, applicantIdNumber: String,filePath: Uri){
//        var id = System.currentTimeMillis().toString()
//        var storageReference = FirebaseStorage.getInstance().getReference().child("Uploads/$id")
//        progress.show()
//
//        storageReference.putFile(filePath).addOnCompleteListener{
//            progress.dismiss()
//            if (it.isSuccessful){
//                // Proceed to store other data into the db
//                storageReference.downloadUrl.addOnSuccessListener {
//                    var imageUrl = it.toString()
//                    var houseData = Upload(id, applicantName, applicantIdNumber, imageUrl)
//                    var dbRef = FirebaseDatabase.getInstance()
//                        .getReference().child("Uploads/$id")
//                    dbRef.setValue(houseData)
//                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
//                }
//            }else{
//                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


    fun viewUploads(upload:MutableState<Upload>, uploads:SnapshotStateList<Upload>): SnapshotStateList<Upload> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Uploads")


        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                uploads.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Upload::class.java)
                    upload.value = value!!
                    uploads.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return uploads
    }


}
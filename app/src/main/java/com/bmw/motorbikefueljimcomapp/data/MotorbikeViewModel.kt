package com.bmw.motorbikefueljimcomapp.data


import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.bmw.motorbikefueljimcomapp.model.Motorbike
import com.google.firebase.firestore.FirebaseFirestore

class MotorbikeViewModel(var navController: NavHostController, var context: Context): ViewModel() {

    var firestore: FirebaseFirestore? = null

    init {
        firestore = FirebaseFirestore.getInstance()
    }

    fun saveMotorbike(numberPlate: String, brand: String, model: String, color: String) {
        // Check if any field is blank
        if (numberPlate.isBlank() || brand.isBlank() || model.isBlank() || color.isBlank()) {
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val motorbikeId = System.currentTimeMillis().toString() // Generate a unique ID (you might use a better method)
        val motorbike = Motorbike(motorbikeId, numberPlate, brand, model, color)

        firestore?.collection("Motorbikes")
            ?.document(motorbikeId)
            ?.set(motorbike)
            ?.addOnSuccessListener {
                Toast.makeText(context, "Motorbike saved successfully", Toast.LENGTH_SHORT).show()
                // You can navigate to another screen here if needed
                // navController.navigate(ROUTE_MOTORBIKE_LIST)
            }
            ?.addOnFailureListener { e ->
                Toast.makeText(context, "Error saving motorbike: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    fun getMotorbikes(): MutableState<List<Motorbike>> {
        val motorbikesListState = mutableStateOf<List<Motorbike>>(emptyList())

        firestore?.collection("Motorbikes")
            ?.get()
            ?.addOnSuccessListener { documents ->
                val motorbikes = mutableListOf<Motorbike>()
                for (document in documents) {
                    val motorbike = document.toObject(Motorbike::class.java)
                    motorbikes.add(motorbike)
                }
                motorbikesListState.value = motorbikes
            }
            ?.addOnFailureListener { e ->
                Toast.makeText(context, "Error fetching motorbikes: ${e.message}", Toast.LENGTH_SHORT).show()
                motorbikesListState.value = emptyList() // Set to empty on failure
            }

        return motorbikesListState
    }

    fun deleteMotorbike(motorbikeId: String) {
        firestore?.collection("Motorbikes")
            ?.document(motorbikeId)
            ?.delete()
            ?.addOnSuccessListener {
                Toast.makeText(context, "Motorbike deleted successfully", Toast.LENGTH_SHORT).show()
                // You might want to refresh the list of motorbikes after deletion
            }
            ?.addOnFailureListener { e ->
                Toast.makeText(context, "Error deleting motorbike: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    fun updateMotorbike(motorbike: Motorbike) {
        firestore?.collection("Motorbikes")
            ?.document(motorbike.id)
            ?.set(motorbike) // Use set to overwrite the document with updated data
            ?.addOnSuccessListener {
                Toast.makeText(context, "Motorbike updated successfully", Toast.LENGTH_SHORT).show()
                // You might want to refresh the list after update
            }
            ?.addOnFailureListener { e ->
                Toast.makeText(context, "Error updating motorbike: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
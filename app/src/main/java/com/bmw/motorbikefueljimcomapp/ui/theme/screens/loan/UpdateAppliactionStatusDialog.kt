package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan



import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.data.productviewmodel
import com.bmw.motorbikefueljimcomapp.model.LoanApplication
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Composable
fun UpdateLoanApplication(navController: NavHostController,id:String) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        var applicantName by remember { mutableStateOf(TextFieldValue("")) }
        var applicantIdNumber by remember { mutableStateOf(TextFieldValue("")) }
        var loanAmount by remember { mutableStateOf(TextFieldValue("")) }
        var loanPurpose by remember { mutableStateOf(TextFieldValue("")) }
        var applicationDate by remember { mutableStateOf(TextFieldValue("")) }
        var status by remember { mutableStateOf(TextFieldValue("")) }
        var note by remember { mutableStateOf(TextFieldValue("")) }
        var currentDataRef = FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")
        currentDataRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var product = snapshot.getValue(LoanApplication::class.java)
                applicantName = TextFieldValue(product!!.applicantName)
                applicantIdNumber = TextFieldValue(product!!.applicantIdNumber)
                loanAmount = TextFieldValue(product!!.loanAmount)
                loanPurpose = TextFieldValue(product!!.loanPurpose)
                applicationDate = TextFieldValue(product!!.applicationDate)
                status =   TextFieldValue(product!!.status)
                note = product!!.note?.let { TextFieldValue(it) }!!
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })

        Text(
            text = "Add product",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )



        OutlinedTextField(
            value = applicantName,
            onValueChange = { applicantName = it },
            label = { Text(text = "Product name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = applicantIdNumber,
            onValueChange = { applicantIdNumber = it },
            label = { Text(text = "Product quantity *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = loanAmount,
            onValueChange = { loanAmount = it },
            label = { Text(text = "Product price *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE UPDATE LOGIC HERE---------------//
            var productRepository = productviewmodel(navController, context)
            productRepository.updateProduct(id,
                applicantName.text.trim(),
                applicantIdNumber.text.trim(),loanAmount.text.trim(),
                loanPurpose.text.trim(),applicationDate.text.trim(),
                status.text.trim(),
                note.text.trim())


        }) {
            Text(text = "Update")
        }

    }
}

@Preview
@Composable
fun update() {
    UpdateLoanApplication(rememberNavController(), id = "")
}
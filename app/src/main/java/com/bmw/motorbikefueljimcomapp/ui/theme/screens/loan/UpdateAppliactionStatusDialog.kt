package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan



import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.bmw.motorbikefueljimcomapp.data.LoanApplicationViewModel
import com.bmw.motorbikefueljimcomapp.model.Applicant
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Composable
fun UpdateLoanScreen(navController: NavHostController,id:String) {
    Column(modifier = Modifier

        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        var context = LocalContext.current
        var name by remember { mutableStateOf("") }
        var IdNumber by remember { mutableStateOf("") }
        var loanAmount by remember { mutableStateOf("") }
        var loanPurpose by remember { mutableStateOf("") }
        var applicationDate by remember { mutableStateOf("") }

        var currentDataRef = FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")
        currentDataRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var product = snapshot.getValue(Applicant::class.java)
                name = product!!.name
                IdNumber = product!!.IdNumber
                loanAmount = product!!.loanAmount
                loanPurpose = product!!.loanPurpose
                applicationDate = product!!.applicationDate
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })

        Text(
            text = "UPDATE LOAN",
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Blue,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
//            textDecoration = TextDecoration.Underline
        )

        var Applicantname by remember { mutableStateOf(TextFieldValue(name)) }
        var ApplicantIdNumber by remember { mutableStateOf(TextFieldValue(IdNumber)) }
        var ApplicantloanAmount by remember { mutableStateOf(TextFieldValue(loanAmount)) }
        var ApplicantloanPurpose by remember { mutableStateOf(TextFieldValue(loanPurpose)) }
        var ApplicantapplicationDate by remember { mutableStateOf(TextFieldValue(applicationDate)) }

        OutlinedTextField(
            value = Applicantname,
            onValueChange = { Applicantname = it },
            label = { Text(text = "Applicant name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

//        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = ApplicantIdNumber,
            onValueChange = { ApplicantIdNumber = it },
            label = { Text(text = "Applicant IdNumber*") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

//        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = ApplicantloanAmount,
            onValueChange = { ApplicantloanAmount = it },
            label = { Text(text = "Loan Amount *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
//        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = ApplicantloanPurpose,
            onValueChange = { ApplicantloanPurpose = it },
            label = { Text(text = "Loan Purpose *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))

        OutlinedTextField(
            value = ApplicantapplicationDate,
            onValueChange = { ApplicantapplicationDate = it },
            label = { Text(text = "Application Date *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE UPDATE LOGIC HERE---------------//
            var productRepository = LoanApplicationViewModel(navController, context)
            productRepository.updateProduct(Applicantname.text.trim(),ApplicantIdNumber.text.trim(),
                ApplicantloanAmount.text.trim(),ApplicantloanPurpose.text.trim(),ApplicantapplicationDate.text.trim(),id)


        },
            modifier = Modifier.padding(15.dp).align(Alignment.CenterHorizontally).width(300.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color.Blue)) {
            Text(text = "Update")
        }

    }
}

@Preview
@Composable
fun update() {
    UpdateLoanScreen(rememberNavController(), id = "")
}
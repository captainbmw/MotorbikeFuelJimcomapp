package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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


@Composable
fun UpdateLoanApplication(navController: NavHostController,id:String) {
    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        var context = LocalContext.current
        var applicantName by remember { mutableStateOf("") }
        var applicantIdNumber by remember { mutableStateOf("") }
        var loanAmount by remember { mutableStateOf("") }
        var loanPurpose by remember { mutableStateOf("") }
        var applicationDate by remember { mutableStateOf("") }
        var status by remember { mutableStateOf("") }
        var note by remember { mutableStateOf("") }




        Text(
            text = "Update Loan",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Blue,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )
        var Name by remember { mutableStateOf(TextFieldValue(applicantName)) }
        var IdNumber by remember { mutableStateOf(TextFieldValue(applicantIdNumber)) }
        var Amount by remember { mutableStateOf(TextFieldValue(loanAmount)) }
        var Purpose by remember { mutableStateOf(TextFieldValue(loanPurpose)) }
        var Date by remember { mutableStateOf(TextFieldValue(applicationDate)) }
        var comments by remember { mutableStateOf(TextFieldValue(status)) }
        var text by remember { mutableStateOf(TextFieldValue(note)) }




        OutlinedTextField(
            value = Name,
            onValueChange = { Name = it },
            label = { Text(text = "ApplicantName *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = IdNumber,
            onValueChange = { IdNumber = it },
            label = { Text(text = "ApplicantIdNumber *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = Amount,
            onValueChange = { Amount = it },
            label = { Text(text = "Loan Amount *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = Purpose,
            onValueChange = { Purpose = it },
            label = { Text(text = "Loan Purpose*") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = Date,
            onValueChange = { Date = it },
            label = { Text(text = "Application Date*") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = comments,
            onValueChange = { comments = it },
            label = { Text(text = "Status*") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )



        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE UPDATE LOGIC HERE---------------//
            var LoanRepository = LoanApplicationViewModel(navController, context)
            LoanRepository.updateProduct(id,
                Name.text.trim(),
                IdNumber.text.trim(),Amount.text.trim(),
                Purpose.text.trim(),Date.text.trim(),
                comments.text.trim(),
                text.text.trim())


        },
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)) {
            Text(text = "Update")
        }

    }
}



@Preview
@Composable
fun update() {
    UpdateLoanApplication(rememberNavController(), id = "")
}
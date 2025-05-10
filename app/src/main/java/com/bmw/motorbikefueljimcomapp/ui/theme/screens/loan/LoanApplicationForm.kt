package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.data.LoanApplicationViewModel
import kotlinx.coroutines.launch


@Composable
fun LoanApplicationForm(

    navController: NavHostController
) {
    var loanAmount by remember { mutableStateOf("") }
    var loanPurpose by remember { mutableStateOf("") }
    var repaymentPlan by remember { mutableStateOf("") }
    var termsAccepted by remember { mutableStateOf(false) }
    var newStatus by remember { mutableStateOf("Pending") }
    var notes by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = {},
        title = { Text("Loan Application Form") },
        text = {
            Column {
                OutlinedTextField(
                    value = loanAmount,
                    onValueChange = { loanAmount = it },
                    label = { Text("Loan Amount") }
                )
                OutlinedTextField(
                    value = loanPurpose,
                    onValueChange = { loanPurpose = it },
                    label = { Text("Loan Purpose") }
                )
                OutlinedTextField(
                    value = repaymentPlan,
                    onValueChange = { repaymentPlan = it },
                    label = { Text("Repayment Plan (e.g., Weekly, Monthly)") }
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = termsAccepted, onCheckedChange = { termsAccepted = it })
                    Text("I accept the terms and conditions")
                }

            }
        },
        confirmButton = {
            Button(onClick = {navController.navigate("loan")
            }) {
                Text("Submit")
            }
        },

        dismissButton = {
            Button(onClick = {navController.navigate("home")}) {
                Text("Cancel")
            }
        }
    )
}

@Preview
@Composable
private fun LoanApplicationFormPreview() {
    LoanApplicationForm(rememberNavController())
    
}

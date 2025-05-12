package com.bmw.motorbikefueljimcomapp.ui.theme.screens.Repayment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.data.RepaymentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepaymentScreen(
    navController: NavHostController,
     // Pass the loan ID to fetch repayments for
   // Get the ViewModel
) {
    val context = LocalContext.current

    // State for adding a new repayment
    var repaymentDate by remember { mutableStateOf(TextFieldValue("")) }
    var loanId by remember { mutableStateOf(TextFieldValue("")) }
   
    var amountPaid by remember { mutableStateOf(TextFieldValue("")) }
    var paymentMethod by remember { mutableStateOf(TextFieldValue("")) }
    var transactionId by remember { mutableStateOf(TextFieldValue("")) }

    // Fetch repayments when the screen is first displayed
    LaunchedEffect(Unit) {

    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Repayments for Loan ID: ") })
        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = {  }) {
//                Icon(Icons.Default.Add, contentDescription = "Add Repayment")
//            }
//
//        },

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = loanId,
                onValueChange = { loanId = it },
                shape = RoundedCornerShape(10.dp),
                label = { Text("Loan ID") },
                

                )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = repaymentDate,
                onValueChange = { repaymentDate = it },
                shape = RoundedCornerShape(10.dp),
                label = { Text("Repayment Date") },

                )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = paymentMethod,
                onValueChange = { paymentMethod = it },
                shape = RoundedCornerShape(10.dp),
                label = { Text("Payment Method") }
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = transactionId,
                onValueChange = { transactionId = it },
                shape = RoundedCornerShape(10.dp),
                label = { Text("Transaction ID (Optional)") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val myrepayment= RepaymentViewModel(navController,context)
                    myrepayment.makepayment(id = "",loanId.text.trim(),
                        repaymentDate.text.trim(),
                        amountPaid.text.trim(),paymentMethod.
                        text.trim(),transactionId.text.trim(),
                        amountPaid.text.trim(),paymentMethod.text.trim(),transactionId.text.trim())


                },
                modifier = Modifier.width(300.dp)

            ) {
                Text("Make Payment",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack() },
                modifier = Modifier.width(300.dp),
                ) {
                Text("Cancel",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,)
            }
        }
    }
}

@Preview
@Composable
private fun repayment() {
    RepaymentScreen(rememberNavController())
    
}

//            when (operationStatus) {
//                OperationStatus.Idle -> {
//                    // Optionally display a message or nothing in idle state
//                }
//                OperationStatus.Loading -> {
//                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
//                    Text("Loading repayments...", modifier = Modifier.align(Alignment.CenterHorizontally))
//                }
//                is OperationStatus.Success -> {
//                    Text(
//                        text = (operationStatus as OperationStatus.Success).message,
//                        color = Color.Green,
//                        modifier = Modifier
//                            .align(Alignment.CenterHorizontally)
//                            .padding(bottom = 8.dp)
//                    )
//                }
//                is OperationStatus.Error -> {
//                    Text(
//                        text = (operationStatus as OperationStatus.Error).message,
//                        color = Color.Red,
//                        modifier = Modifier
//                            .align(Alignment.CenterHorizontally)
//                            .padding(bottom = 8.dp)
//                    )
//                }
//            }
//
//            if (repayments.isEmpty() && operationStatus != OperationStatus.Loading) {
//                Text("No repayments found for this loan.", modifier = Modifier.align(Alignment.CenterHorizontally))
//            } else {
//                LazyColumn {
//                    items(repayments) { repayment ->
//                        RepaymentItem(repayment = repayment)
//                        Divider() // Add a divider between items
//                    }
//                }
//            }
//        }
//    }
//
//    // Add Repayment Dialog
//    if (showAddRepaymentDialog) {
//        AlertDialog(
//            onDismissRequest = { showAddRepaymentDialog = false },
//            title = { Text("Add New Repayment") },
//            text = {
//                Column {
//                    OutlinedTextField(
//                        value = amountPaidText,
//                        onValueChange = { amountPaidText = it },
//                        label = { Text("Amount Paid") },
//
//                    )
//                    OutlinedTextField(
//                        value = paymentMethodText,
//                        onValueChange = { paymentMethodText = it },
//                        label = { Text("Payment Method") }
//                    )
//                    OutlinedTextField(
//                        value = transactionIdText,
//                        onValueChange = { transactionIdText = it },
//                        label = { Text("Transaction ID (Optional)") }
//                    )
//                }
//            },
//            confirmButton = {
//                Button(
//                    onClick = {
//                        val amount = amountPaidText.toDoubleOrNull()
//                        if (amount != null && paymentMethodText.isNotBlank()) {
//                            repaymentViewModel.addRepayment(
//                                loanId = loanId,
//                                amountPaid = amount,
//                                paymentMethod = paymentMethodText,
//                                transactionId = transactionIdText.ifBlank { null }
//                            )
//                            showAddRepaymentDialog = false
//                            // Clear fields after adding
//                            amountPaidText = ""
//                            paymentMethodText = ""
//                            transactionIdText = ""
//                        } else {
//                            // Show an error message to the user
//                            // You might use a Toast or a Snackbar for this
//                        }
//                    }
//                ) {
//                    Text("Add")
//                }
//            },
//            dismissButton = {
//                Button(onClick = { showAddRepaymentDialog = false }) {
//                    Text("Cancel")
//                }
//            }
//        )
//    }
//}
//
//@Composable
//fun RepaymentItem(repayment: Repayment) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//    ) {
//        Text(text = "Amount: ${repayment.amountPaid}", fontWeight = FontWeight.Bold)
//        Text(text = "Method: ${repayment.paymentMethod}")
//        repayment.transactionId?.let {
//            Text(text = "Transaction ID: $it")
//        }
//        Text(text = "Date: ${formatTimestamp(repayment.repaymentDate)}")
//    }
//}
//
//fun formatTimestamp(timestamp: Long): String {
//    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
//    return sdf.format(Date(timestamp))
//}


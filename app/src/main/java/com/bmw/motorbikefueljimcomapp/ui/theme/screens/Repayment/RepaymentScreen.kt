package com.bmw.motorbikefueljimcomapp.ui.theme.screens.Repayment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.bmw.motorbikefueljimcomapp.data.RepaymentViewModel
import com.bmw.motorbikefueljimcomapp.model.OperationStatus
import com.bmw.motorbikefueljimcomapp.model.Repayment
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_HOME
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepaymentScreen(
    loanId: String,
    navController: NavHostController,
    repaymentViewModel: RepaymentViewModel = viewModel()
) {
    val repayments = repaymentViewModel.repaymentsForLoan.observeAsState(initial = emptyList())
    val operationStatus = repaymentViewModel.operationStatus.observeAsState()

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(loanId) {
        repaymentViewModel.getRepaymentsForLoan(loanId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Repayments for Loan: $loanId") },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(ROUTE_HOME)}) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("Add Repayment")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (repayments.value.isEmpty()) {
                Text("No repayments recorded yet for this loan.")
            } else {
                LazyColumn {
                    items(repayments.value) { repayment ->
                        RepaymentItem(repayment = repayment)
                        Divider()
                    }
                }
            }

            // Display operation status
            operationStatus.value?.let { status ->
                Spacer(modifier = Modifier.height(16.dp))
                when (status) {
                    is OperationStatus.Success -> Text(status.message, color = MaterialTheme.colorScheme.primary)
                    is OperationStatus.Error -> Text(status.message, color = MaterialTheme.colorScheme.error)
                    OperationStatus.Loading -> CircularProgressIndicator()
                    else -> {} // Handle other states if needed
                }
            }

            if (showDialog) {
                AddRepaymentDialog(
                    loanId = loanId,
                    onRepaymentRecorded = { showDialog = false },
                    onDismiss = { showDialog = false },
                    repaymentViewModel = repaymentViewModel
                )
            }
        }
    }
}

@Composable
fun RepaymentItem(repayment: Repayment) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Date: ${SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(repayment.repaymentDate))}")
        Text("Amount Paid: ${repayment.amountPaid}")
        Text("Payment Method: ${repayment.paymentMethod}")
        repayment.transactionId?.let { Text("Transaction ID: $it") }
    }
}

@Composable
fun AddRepaymentDialog(
    loanId: String,
    onRepaymentRecorded: () -> Unit,
    onDismiss: () -> Unit,
    repaymentViewModel: RepaymentViewModel
) {
    var amountPaid by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("") }
    var transactionId by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Record Repayment") },
        text = {
            Column {
                OutlinedTextField(
                    value = amountPaid,
                    onValueChange = { amountPaid = it },
                    label = { Text("Amount Paid") }
                )
                OutlinedTextField(
                    value = paymentMethod,
                    onValueChange = { paymentMethod = it },
                    label = { Text("Payment Method") }
                )
                OutlinedTextField(
                    value = transactionId,
                    onValueChange = { transactionId = it },
                    label = { Text("Transaction ID (Optional)") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val amount = amountPaid.toDoubleOrNull()
                    if (amount != null && amount > 0) {
                        val repayment = Repayment(
                            loanId = loanId,
                            amountPaid = amount,
                            paymentMethod = paymentMethod,
                            transactionId = transactionId.ifBlank { null }
                        )
                        repaymentViewModel.recordRepayment(repayment) {
                            onRepaymentRecorded()
                            amountPaid = ""
                            paymentMethod = ""
                            transactionId = ""
                        }
                    }
                }
            ) {
                Text("Record")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview
@Composable
private fun RepaymentScreenPreview() {
    RepaymentScreen(loanId = "1", rememberNavController())
    RepaymentItem(repayment = Repayment(loanId = "1", amountPaid = 100.0, paymentMethod = "Credit Card", transactionId = "12345"))
    AddRepaymentDialog(loanId = "1", onRepaymentRecorded = {}, onDismiss = {}, repaymentViewModel = viewModel())



}
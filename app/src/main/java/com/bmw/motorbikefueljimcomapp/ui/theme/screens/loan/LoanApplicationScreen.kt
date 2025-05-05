package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import com.bmw.motorbikefueljimcomapp.data.LoanApplicationViewModel
import com.bmw.motorbikefueljimcomapp.model.LoanApplication
import com.bmw.motorbikefueljimcomapp.model.OperationStatus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanApplicationScreen(
    ownerId: String,  // Pass the Owner ID here
    motorbikeId: String, // Pass Motorbike ID
    onNavigateBack: () -> Unit,
    onNavigateToLoanDetails: (String) -> Unit, // Add this for navigation
    loanApplicationViewModel: LoanApplicationViewModel = viewModel(),
    navHostController: NavHostController
) {
    val loanApplications = loanApplicationViewModel.loanApplications.observeAsState(emptyList())
    val operationStatus = loanApplicationViewModel.operationStatus.observeAsState()

    var showApplicationForm by remember { mutableStateOf(false) }

    // Fetch loan applications
    LaunchedEffect(Unit) {
        loanApplicationViewModel.getLoanApplications()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Loan Applications") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showApplicationForm = true }) {
                Text("Apply for Loan")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (loanApplications.value.isEmpty()) {
                Text("No loan applications found.")
            } else {
                LazyColumn {
                    items(loanApplications.value) { application ->
                        LoanApplicationItem(
                            application = application,
                            onViewDetails = { onNavigateToLoanDetails(application.id) } // Use the new lambda
                        )
//                        Divider()
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
                    else -> {}
                }
            }

            if (showApplicationForm) {
                LoanApplicationForm(
                    ownerId = ownerId,
                    motorbikeId = motorbikeId,
                    onApplicationSubmitted = {
                        showApplicationForm = false
                        loanApplicationViewModel.getLoanApplications() // Refresh list
                    },
                    onDismiss = { showApplicationForm = false },
                    loanApplicationViewModel = loanApplicationViewModel
                )
            }
        }
    }
}

@Composable
fun LoanApplicationItem(
    application: LoanApplication,
    onViewDetails: () -> Unit // Add this lambda parameter
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        Text("Application Date: ${
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
                Date(application.applicationDate)
            )
        }")
        Text("Loan Amount: ${application.loanAmount}")
        Text("Status: ${application.status}", fontWeight = FontWeight.Bold)
        Button(onClick = onViewDetails) {  // Use the lambda here
            Text("View Details")
        }
    }
}

@Composable
fun LoanApplicationForm(
    ownerId: String,
    motorbikeId: String,
    onApplicationSubmitted: () -> Unit,
    onDismiss: () -> Unit,
    loanApplicationViewModel: LoanApplicationViewModel
) {
    var loanAmount by remember { mutableStateOf("") }
    var loanPurpose by remember { mutableStateOf("") }
    var repaymentPlan by remember { mutableStateOf("") }
    var termsAccepted by remember { mutableStateOf(false) }
    // Add states for other fields as necessary

    AlertDialog(
        onDismissRequest = onDismiss,
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
                // Add more input fields for other loan application details
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val amount = loanAmount.toDoubleOrNull()
                    if (amount != null && amount > 0 && termsAccepted) {
                        val application = LoanApplication(
                            ownerId = ownerId,
                            motorbikeId = motorbikeId,
                            loanAmount = amount,
                            loanPurpose = loanPurpose,
                            repaymentPlan = repaymentPlan,
                            termsAndConditionsAccepted = termsAccepted
                            // Set other fields
                        )
                        loanApplicationViewModel.submitLoanApplication(application, onApplicationSubmitted)
                        // Clear the form
                        loanAmount = ""
                        loanPurpose = ""
                        repaymentPlan = ""
                        termsAccepted = false
                    }
                }
            ) {
                Text("Submit Application")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanApplicationDetailsScreen(
    applicationId: String,
    onNavigateBack: () -> Unit,
    loanApplicationViewModel: LoanApplicationViewModel = viewModel()
) {
    var loanApplication by remember { mutableStateOf<LoanApplication?>(null) }
    val operationStatus = loanApplicationViewModel.operationStatus.observeAsState()
    var showUpdateStatusDialog by remember { mutableStateOf(false) }

    LaunchedEffect(applicationId) {
        loanApplication = loanApplicationViewModel.getLoanApplicationById(applicationId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Loan Application Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
        ) {
            if (loanApplication != null) {
                val application = loanApplication!!
                Text("Application ID: ${application.id}", fontWeight = FontWeight.Bold)
                Text("Owner ID: ${application.ownerId}")
                Text("Motorbike ID: ${application.motorbikeId}")
                Text("Loan Amount: ${application.loanAmount}")
                Text("Application Date: ${
                    SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(
                        Date(application.applicationDate)
                    )
                }")
                Text("Status: ${application.status}", fontWeight = FontWeight.Bold)
                Text("Loan Purpose: ${application.loanPurpose}")
                Text("Repayment Plan: ${application.repaymentPlan}")

                if (application.notes != null) {
                    Text("Notes: ${application.notes}")
                }
                // Display other application details

                Spacer(modifier = Modifier.height(16.dp))
                if (application.status == "Pending") {
                    Button(onClick = { showUpdateStatusDialog = true }) {
                        Text("Update Status")
                    }
                }
            } else {
                Text("Loading application details...")
            }

            operationStatus.value?.let { status ->
                Spacer(modifier = Modifier.height(16.dp))
                when (status) {
                    is OperationStatus.Success -> Text(status.message, color = MaterialTheme.colorScheme.primary)
                    is OperationStatus.Error -> Text(status.message, color = MaterialTheme.colorScheme.error)
                    OperationStatus.Loading -> CircularProgressIndicator()
                    else -> {}
                }
            }
        }
    }

    if (showUpdateStatusDialog) {
        UpdateApplicationStatusDialog(
            applicationId = applicationId,
            onDismiss = { showUpdateStatusDialog = false },
            onStatusUpdated = {
                showUpdateStatusDialog = false
                // Refresh the application details
                suspend fun someOtherFunction(applicationId: String): LoanApplication? {
                    return loanApplicationViewModel.getLoanApplicationById(applicationId)
                }
            },
            loanApplicationViewModel = loanApplicationViewModel
        )
    }
}

@Composable
fun UpdateApplicationStatusDialog(
    applicationId: String,
    onDismiss: () -> Unit,
    onStatusUpdated: () -> Unit,
    loanApplicationViewModel: LoanApplicationViewModel
) {
    var newStatus by remember { mutableStateOf("Pending") }
    var notes by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Update Application Status") },
        text = {
            Column {
                DropdownMenu(
                    expanded = true, // Keep it expanded for simplicity
                    onDismissRequest = { },
                ) {
//                    DropdownMenuItem(onClick = { newStatus = "Pending" }) {
//                        Text("Pending")
//                    }
//                    DropdownMenuItem(onClick = { newStatus = "Approved" }) {
//                        Text("Approved")
//                    }
//                    DropdownMenuItem(onClick = { newStatus = "Rejected" }) {
//                        Text("Rejected")
//                    }
                }
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes (Optional)") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    loanApplicationViewModel.updateLoanApplicationStatus(applicationId, newStatus, notes)
                    onStatusUpdated()
                }
            ) {
                Text("Update")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


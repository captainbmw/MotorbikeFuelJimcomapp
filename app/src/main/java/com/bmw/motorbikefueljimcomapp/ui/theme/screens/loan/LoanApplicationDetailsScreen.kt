package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bmw.motorbikefueljimcomapp.data.LoanApplicationViewModel
import com.bmw.motorbikefueljimcomapp.model.LoanApplication
import com.bmw.motorbikefueljimcomapp.model.OperationStatus
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanApplicationDetailsScreen(
    applicationId: String,
    onNavigateBack: () -> Unit,
    loanApplicationViewModel: LoanApplicationViewModel = viewModel()
) {
    var loanApplication by remember { mutableStateOf<LoanApplication?>(null) }
    val operationStatus by loanApplicationViewModel.operationStatus.observeAsState()
    var showUpdateStatusDialog by remember { mutableStateOf(false) }

    LaunchedEffect(applicationId) {
        loanApplicationViewModel.viewModelScope.launch {
            loanApplication = null
            val application = loanApplicationViewModel.getLoanApplicationById(applicationId)
            loanApplication = application
        }
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
            when (operationStatus) {
                is OperationStatus.Loading -> CircularProgressIndicator()
                is OperationStatus.Error -> Text(
                    text = (operationStatus as OperationStatus.Error).message,
                    color = MaterialTheme.colorScheme.error
                )

                is OperationStatus.Success -> {
                    if (loanApplication != null) {
                        val application = loanApplication!!
                        Text("Application ID: ${application.id}", fontWeight = FontWeight.Bold)
                        Text("Owner ID: ${application.ownerId}")
                        Text("Motorbike ID: ${application.motorbikeId}")
                        Text("Loan Amount: ${application.loanAmount}")
                        Text("Interest Rate: ${application.interestRate}")
                        Text("Purpose: ${application.loanPurpose}")
                        Text("Repayment Plan: ${application.repaymentPlan}")
                        Text("Status: ${application.status}")
                        Text("Notes: ${application.notes ?: "None"}")

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(onClick = { showUpdateStatusDialog = true }) {
                            Text("Update Status")
                        }

                        if (showUpdateStatusDialog) {
                            UpdateApplicationStatusDialog(
                                application = application,
                                onDismiss = { showUpdateStatusDialog = false },
                                onStatusUpdated = { updatedStatus, notes ->
                                    loanApplicationViewModel.updateLoanApplicationStatus(
                                        application.id,
                                        updatedStatus,
                                        notes
                                    )
                                    showUpdateStatusDialog = false
                                }
                            )
                        }
                    }
                }
                else -> {}
            }
        }
    }
}
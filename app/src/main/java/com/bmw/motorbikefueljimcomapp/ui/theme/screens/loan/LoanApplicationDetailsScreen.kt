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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        loanApplicationViewModel.viewModelScope.launch {
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
                loanApplicationViewModel.viewModelScope.launch {
                    val newApplication = loanApplicationViewModel.getLoanApplicationById(applicationId)
                    loanApplication = newApplication
                }
            },
            loanApplicationViewModel = loanApplicationViewModel
        )
    }
}
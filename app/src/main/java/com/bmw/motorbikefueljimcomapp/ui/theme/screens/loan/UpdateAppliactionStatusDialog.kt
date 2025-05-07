package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bmw.motorbikefueljimcomapp.data.LoanApplicationViewModel


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
                    DropdownMenuItem(
                        text = { Text("Pending") },
                        onClick = { newStatus = "Pending" })
                    DropdownMenuItem(
                        text = { Text("Approved") },
                        onClick = { newStatus = "Approved" })
                    DropdownMenuItem(
                        text = { Text("Rejected") },
                        onClick = { newStatus = "Rejected" })
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
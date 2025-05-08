package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bmw.motorbikefueljimcomapp.model.LoanApplication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateApplicationStatusDialog(
    application: LoanApplication,
    onDismiss: () -> Unit,
    onStatusUpdated: (String, String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf(application.status) }
    var notes by remember { mutableStateOf(application.notes ?: "") }
    val statusOptions = listOf("Pending", "Approved", "Rejected")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Update Application Status") },
        text = {
            Column {
                Text("Current Status: ${application.status}")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { expanded = true }) {
                    Text(selectedStatus)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    statusOptions.forEach { status ->
                        DropdownMenuItem(
                            text = { Text(status) },
                            onClick = {
                                selectedStatus = status
                                expanded = false
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") },
                    modifier = Modifier.padding(8.dp)
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onStatusUpdated(selectedStatus, notes)
            }) {
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
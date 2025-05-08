package com.bmw.motorbikefueljimcomapp.ui.theme.screens.owner

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bmw.motorbikefueljimcomapp.data.AuthViewModel
import com.bmw.motorbikefueljimcomapp.data.OwnerRegistrationViewModel
import com.bmw.motorbikefueljimcomapp.data.entities.OwnerEntity
import com.bmw.motorbikefueljimcomapp.model.OperationStatus


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerRegistrationScreen(
    navController: Dp,
    onNavigateBack: () -> Unit,
    ownerRegistrationViewModel: OwnerRegistrationViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel() // To get current user if needed
) {
    var fullName by remember { mutableStateOf("") }
    var idNumber by remember { mutableStateOf("") }
    var kraPin by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var registrationDate by remember { mutableStateOf("") } // Consider using a Date picker

    val registrationStatus = ownerRegistrationViewModel.registrationStatus.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Owner Registration") },
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
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(value = fullName, onValueChange = { fullName = it }, label = { Text("Full Name") })
            OutlinedTextField(value = idNumber, onValueChange = { idNumber = it }, label = { Text("ID Number") })
            OutlinedTextField(value = kraPin, onValueChange = { kraPin = it }, label = { Text("KRA PIN") })
            OutlinedTextField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = { Text("Phone Number") })
            OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
            OutlinedTextField(value = registrationDate, onValueChange = { registrationDate = it }, label = { Text("Registration Date (YYYY-MM-DD)") })

            Button(onClick = {
                val owner = OwnerEntity(
                    fullName = fullName,
                    idNumber = idNumber,
                    kraPin = kraPin,
                    phoneNumber = phoneNumber,
                    address = address,
                    registrationDate = registrationDate,
                    id = TODO(),
                    status = TODO()
                )
                ownerRegistrationViewModel.registerOwner(owner) {
                    // Optionally navigate or show a success message
                    fullName = ""
                    idNumber = ""
                    kraPin = ""
                    phoneNumber = ""
                    address = ""
                    registrationDate = ""
                }
            }) {
                Text("Register Owner")
            }

            registrationStatus.value?.let { status ->
                when (status) {
                    is OperationStatus.Success -> Text(status.message, color = MaterialTheme.colorScheme.primary)
                    is OperationStatus.Error -> Text(status.message, color = MaterialTheme.colorScheme.error)
                    OperationStatus.Loading -> CircularProgressIndicator()
                    null -> {}
                    OperationStatus.Idle -> TODO()
                }
            }
        }
    }
}

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.data.AuthViewModel
import com.bmw.motorbikefueljimcomapp.data.OwnerRegistrationViewModel
import com.bmw.motorbikefueljimcomapp.data.entities.OwnerEntity
import com.bmw.motorbikefueljimcomapp.model.OperationStatus


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerRegistrationScreen(
    navController: NavHostController,
) {
    var fullName by remember { mutableStateOf(TextFieldValue("")) }
    var idNumber by remember { mutableStateOf(TextFieldValue("")) }
    var kraPin by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var registrationDate by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var confirmpass by remember { mutableStateOf(TextFieldValue("")) }
    var context= LocalContext.current



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Owner Registration") },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate("home")}) {
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
            OutlinedTextField(value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") })
            OutlinedTextField(value = idNumber,
                onValueChange = { idNumber = it },
                label = { Text("ID Number") })
            OutlinedTextField(value = kraPin,
                onValueChange = { kraPin = it },
                label = { Text("KRA PIN") })
            OutlinedTextField(value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") })
            OutlinedTextField(value = address,
                onValueChange = { address = it },
                label = { Text("Address") })
            OutlinedTextField(value = registrationDate,
                onValueChange = { registrationDate = it },
                label = { Text("Registration Date (YYYY-MM-DD)") })
            OutlinedTextField(value = pass,
                onValueChange = { pass = it },
                label = { Text("Password") })
            OutlinedTextField(value = confirmpass,
                onValueChange = { confirmpass = it },
                label = { Text("Confirm Password") })

            Button(onClick = {
                val myregister= OwnerRegistrationViewModel(navController,context)
                myregister.signup(fullName.text.trim(),idNumber.text.trim(),kraPin.text.trim(),
                    phoneNumber.text.trim(),address.text.trim(),registrationDate.text.trim(),
                    pass.text.trim(),confirmpass.text.trim())
            }) {
                Text("Register Owner")
            }


        }
    }
}

@Preview
@Composable
private fun OwnerRegistrationScreenPreview() {
    OwnerRegistrationScreen(rememberNavController())
    
}

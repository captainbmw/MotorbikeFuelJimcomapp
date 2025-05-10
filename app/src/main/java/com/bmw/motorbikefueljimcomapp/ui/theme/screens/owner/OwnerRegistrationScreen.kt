package com.bmw.motorbikefueljimcomapp.ui.theme.screens.owner

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
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
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_HOME
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOGIN
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_REGISTER


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerRegistrationScreen(
    navController: NavHostController,
) {
    var fullName by remember { mutableStateOf(TextFieldValue("")) }
    var idNumber by remember { mutableStateOf(TextFieldValue("")) }
    var confirmidNumber by remember { mutableStateOf(TextFieldValue("")) }
    var kraPin by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var registrationDate by remember { mutableStateOf(TextFieldValue("")) }

    var context= LocalContext.current



    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                NavigationBarItem(onClick = { navController.navigate(ROUTE_HOME)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Add New Owner") },
                    label = { Text("Home") },
                    selected = false)
                NavigationBarItem(onClick = { navController.navigate(ROUTE_LOGIN)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Login") },
                    label = { Text("Login") },
                    selected = false)

                NavigationBarItem(onClick = { navController.navigate(ROUTE_REGISTER)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Register") },
                    label = { Text("Register") },
                    selected = false)

            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Owner Registration",
                    fontFamily = FontFamily.Cursive,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ) },
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

            Button(onClick = {
                val myregister= OwnerRegistrationViewModel(navController,context)
                myregister.signup(fullName.text.trim(),idNumber.text.trim(),kraPin.text.trim(),
                    phoneNumber.text.trim(),address.text.trim(),registrationDate.text.trim(),
                    confirmidNumber.text.trim())
            },
                modifier = Modifier.width(300.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
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

package com.bmw.motorbikefueljimcomapp.ui.theme.screens.owner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.R
import com.bmw.motorbikefueljimcomapp.data.OwnerRegistrationViewModel
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_HOME
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOGIN
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_OWNER


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerRegistrationScreen(
    navController: NavHostController,
) {
    var fullName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current


    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Owner Registration",
                    fontFamily = FontFamily.Monospace,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ) },
//                navigationIcon = {
//                    IconButton(onClick = {navController.navigate("home")}) {
//                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
//                    }
//                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = fullName,
                onValueChange = { fullName = it },
                shape = RoundedCornerShape(16.dp),
                label = { Text("Full Name") })
            OutlinedTextField(value = email,
                onValueChange = { email = it },
                shape = RoundedCornerShape(16.dp),
                label = { Text("Enter Email") })
            // Password Input with Show/Hide Toggle
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    val image = if (passwordVisible) painterResource(R.drawable.visible) else painterResource(R.drawable.visibilityoff)
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password")
                    }
                }
            )
            // Confirm Password Input Field with Show/Hide Toggle
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Confirm Password Icon") },
                trailingIcon = {
                    val image = if (confirmPasswordVisible) painterResource(R.drawable.visible)  else painterResource(R.drawable.visibilityoff)
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(image, contentDescription = if (confirmPasswordVisible) "Hide Password" else "Show Password")
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

            )
            Button(onClick = {
                val myregister= OwnerRegistrationViewModel(navController,context)
                myregister.signup(fullName.text.trim(),email.text.trim(),
                    password.text.trim(),
                    confirmPassword.text.trim())
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

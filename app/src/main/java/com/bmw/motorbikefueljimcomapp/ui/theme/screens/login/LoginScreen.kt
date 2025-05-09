package com.bmw.motorbikefueljimcomapp.ui.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.bmw.motorbikefueljimcomapp.R
import com.bmw.motorbikefueljimcomapp.data.AuthViewModel
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOGIN
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_OWNER
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_REGISTER
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_REPAYMENT


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController:NavHostController) {

    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var context= LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "Motorbike Fuel Jimcom App",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(start = 16.dp),
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(ROUTE_OWNER) }) {
                Icon(Icons.Filled.Add, contentDescription = "Add New Owner")
            }
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                NavigationBarItem(onClick = { navController.navigate(ROUTE_LOGIN)},
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Add New Owner") },
                    label = { Text("Home") },
                    selected = true)
                NavigationBarItem(onClick = { navController.navigate(ROUTE_LOGIN)},
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Login") },
                    label = { Text("Login") },
                    selected = true)
                NavigationBarItem(onClick = { navController.navigate(ROUTE_REPAYMENT)},
                    icon = { Icon(Icons.Filled.MoreVert, contentDescription = "Add New Owner") },
                    label = { Text("Repayment") },
                    selected = true)
                NavigationBarItem(onClick = { navController.navigate(ROUTE_REGISTER)},
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Register") },
                    label = { Text("Register") },
                    selected = true)

            }
        }

    ) {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.bike4), contentScale = ContentScale.FillBounds),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = "Login Here",
                color = Color.Cyan,
                fontFamily = FontFamily.Monospace,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value =email , onValueChange = {email=it},
                label = { Text(text = "Enter Email") },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White),

                )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value =pass , onValueChange = {pass=it},
                label = { Text(text = "Enter Password") },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                val mylogin= AuthViewModel(navController, context )
                mylogin.login(email.text.trim(),pass.text.trim())
            }, modifier = Modifier.width(300.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = "Log In",
                    color = Color.Green)
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text( "Don't have account? Click to Register",
                modifier = Modifier.clickable { navController.navigate(ROUTE_REGISTER) },
                color = Color.Blue,
                fontSize = 20.sp)
        }


    }
    }
@Preview
@Composable
fun Loginpage() {
    LoginScreen(rememberNavController())
}
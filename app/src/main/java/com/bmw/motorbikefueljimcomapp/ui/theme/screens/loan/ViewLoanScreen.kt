package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.data.LoanApplicationViewModel
import com.bmw.motorbikefueljimcomapp.model.Applicant
import com.bmw.motorbikefueljimcomapp.model.Upload
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_UPDATE


@Composable
fun ViewLoanScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var productRepository = LoanApplicationViewModel(navController, context)
        val emptyProductState = remember { mutableStateOf(Applicant("","","","","","")) }
        var emptyProductsListState = remember { mutableStateListOf<Applicant>() }
        var Product = remember { mutableStateOf(mutableStateOf(Upload("", "", "", "", "", ""))) }
        var Products = remember { mutableStateListOf<Upload>() }
        productRepository.viewProducts(emptyProductState, emptyProductsListState)

        var products = productRepository.viewProducts(emptyProductState, emptyProductsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "VIEW LOANS",
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.Blue)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(products){
                    ProductItem(
                        name = it.name,
                        IdNumber =  it.IdNumber,
                        loanAmount = it.loanAmount,
                        loanPurpose = it.loanPurpose,
                        applicationDate = it.applicationDate,
                        id = it.id,
                        navController = navController,
                        productRepository = productRepository
                    )
                }
            }
        }
    }

}

@Composable
fun ProductItem(name:String, IdNumber:String, loanAmount:String,applicationDate:String,loanPurpose:String, id:String,
                navController:NavHostController, productRepository: LoanApplicationViewModel) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = name)
        Text(text = IdNumber)
        Text(text = loanAmount)
        Text(text = loanPurpose)
        Text(text = applicationDate)
        Button(onClick = {
            productRepository.deleteProduct(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate(ROUTE_UPDATE+"/$id")
        }) {
            Text(text = "Update")
        }
    }

}

@Preview
@Composable
fun view() {
    ViewLoanScreen(rememberNavController())

}
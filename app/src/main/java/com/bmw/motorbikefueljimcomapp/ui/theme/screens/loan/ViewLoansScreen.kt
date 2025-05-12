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
import com.bmw.motorbikefueljimcomapp.data.productviewmodel
import com.bmw.motorbikefueljimcomapp.model.LoanApplication
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_REPAYMENT


@Composable
fun ViewLoanScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var productRepository = productviewmodel(navController, context)
        val emptyProductState = remember { mutableStateOf(LoanApplication("","","","",
            "","","","")) }
        var emptyProductsListState = remember { mutableStateListOf<LoanApplication>() }

        var products = productRepository.viewProducts(emptyProductState, emptyProductsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All products",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(products){
                    ProductItem(
                        applicantName = it.applicantName,
                        applicantIdNumber = it.applicantIdNumber,
                        loanAmount = it.loanAmount,
                        loanPurpose = it.loanPurpose,
                        applicationDate = it.applicationDate,
                        status = it.status,
                        id = it.id,
                        navController = navController,
                        productRepository = productRepository,
                        note = it.note!!
                    )
                }
            }
        }
    }

}

@Composable
fun ProductItem(id:String,applicantName:String,applicantIdNumber:String,loanAmount:String,
                loanPurpose:String,applicationDate:String,status:String,note:String,
                navController:NavHostController, productRepository: productviewmodel
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = applicantName)
        Text(text = applicantIdNumber)
        Text(text = loanAmount)
        Text(text = loanPurpose)
        Text(text = applicationDate)
        Text(text = status)
        Text(text = note)
        Button(onClick = {
            productRepository.deleteProduct(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate(ROUTE_REPAYMENT+"/$id")
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

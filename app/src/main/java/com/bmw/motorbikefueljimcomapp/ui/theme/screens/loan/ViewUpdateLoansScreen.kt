package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan



import androidx.compose.foundation.background
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
import com.bmw.motorbikefueljimcomapp.model.Upload
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_UPDATE


@Composable
fun ViewUploadLoans(navController:NavHostController) {


        var context = LocalContext.current
        var productRepository = LoanApplicationViewModel(navController, context)
        var loan = remember { mutableStateOf(Upload("","","","","")) }
        var Loans = remember { mutableStateListOf<Upload>() }
        productRepository.viewProducts(loan, Loans)


        val emptyUploadState = remember { mutableStateOf(Upload("","","","","")) }
        var emptyUploadsListState = remember { mutableStateListOf<Upload>() }

        var uploads = productRepository.viewUploads(emptyUploadState, emptyUploadsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All Upload Loans" ,
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.background(Color.Blue)
            ){
                items(uploads){
                    UploadItem(
                        applicantName = it.applicantName,
                        applicantIdNumber = it.applicantIdNumber,
                        loanAmount = it.loanAmount,
                        applicationDate = it.applicationDate,
                        status = it.status,
                        note = it.note.toString(),
                        id = it.id,
                        navController = navController,
                        productRepository = productRepository,
                        loanPurpose = it.loanPurpose
                    )
                }
            }
        }

}


@Composable
fun UploadItem(id: String,applicantName: String, applicantIdNumber: String,
               loanAmount: String,
               loanPurpose: String,
               applicationDate: String,
               status: String, note: String,
               navController:NavHostController, productRepository: LoanApplicationViewModel) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = applicantName)
        Text(text = applicantIdNumber)
        Text(text = loanAmount)
        Text(text = loanPurpose)
        Text(text = applicationDate)
        Text(text = status)
        Text(text = note)
//        Image(
//            painter = rememberAsyncImagePainter(imageUrl),
//            contentDescription = null,
//            modifier = Modifier.size(128.dp)
//        )
        Button(onClick = {
            productRepository.deleteProduct(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate(ROUTE_UPDATE +"/$id")
        }) {
            Text(text = "Update")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun View() {
    ViewUploadLoans(rememberNavController())
    
}

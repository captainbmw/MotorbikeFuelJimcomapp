package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.data.LoanApplicationViewModel
import com.bmw.motorbikefueljimcomapp.model.Applicant
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_HOME
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_UPDATE
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_VIEW_LOAN


@Composable
fun ApplyLoanScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        var context = LocalContext.current
        Text(
            text = "Loan Application",
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.Blue,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,

        )

        var Applicantname by remember { mutableStateOf(TextFieldValue("")) }
        var ApplicantIdNumber by remember { mutableStateOf(TextFieldValue("")) }
        var ApplicantloanAmount by remember { mutableStateOf(TextFieldValue("")) }
        var ApplicantloanPurpose by remember { mutableStateOf(TextFieldValue("")) }
        var applicationDate by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value = Applicantname,
            onValueChange = { Applicantname = it },
            label = { Text(text = "ApplicantName *") },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value =   ApplicantIdNumber,
            onValueChange = { ApplicantIdNumber = it },
            label = { Text(text = "Applicant IdNumber  *") },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = ApplicantloanAmount,
            onValueChange = { ApplicantloanAmount = it },
            label = { Text(text = "Enter Loan Amount  *") },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = ApplicantloanPurpose,
            onValueChange = { ApplicantloanPurpose = it },
            label = { Text(text = "Loan Purpose *") },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = applicationDate,
            onValueChange = { applicationDate = it },
            label = { Text(text = "Application Date *") },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )


        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE SAVE LOGIC HERE---------------//
            var productRepository = LoanApplicationViewModel(navController,context)
            productRepository.saveProduct(Applicantname.text.trim(),ApplicantIdNumber.text.trim(),
                ApplicantloanAmount.text.trim(),ApplicantloanPurpose.text.trim(),applicationDate.text.trim(),)
            navController.navigate(ROUTE_VIEW_LOAN)


        },
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Submit",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                )
        }
        Spacer(modifier = Modifier.height(20.dp))

        //---------------------IMAGE PICKER START-----------------------------------//

//        ImagePicker(Modifier,context, navController, applicantName.text.trim(),applicationDate.text.trim(),applicationDate.text.trim())

        //---------------------IMAGE PICKER END-----------------------------------//

    }
}
//
//@Composable
//fun ImagePicker(modifier: Modifier = Modifier, context: Context, navController: NavHostController, name:String, quantity:String, price:String) {
//    var hasImage by remember { mutableStateOf(false) }
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//
//    val imagePicker = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent(),
//        onResult = { uri ->
//            hasImage = uri != null
//            imageUri = uri
//        }
//    )
//
//    Column(modifier = modifier,) {
//        if (hasImage && imageUri != null) {
//            val bitmap = MediaStore.Images.Media.
//            getBitmap(context.contentResolver,imageUri)
//            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
//        }
//        Column(
//            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
////            Button(
////                onClick = {
////                    imagePicker.launch("image/*")
////                },
////            ) {
////                Text(
////                    text = "Select Image"
////                )
////            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Button(onClick = {
//                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
//                var productRepository = productviewmodel(navController,context)
//                productRepository.saveProductWithImage(name, quantity, price,imageUri!!)
//
//
//            }) {
//                Text(text = "Upload")
//            }
//        }
//    }
//}
//

@Preview
@Composable
fun Addpr() {
    ApplyLoanScreen(rememberNavController())

}
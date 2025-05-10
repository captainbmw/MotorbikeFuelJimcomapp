package com.bmw.motorbikefueljimcomapp.data


import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.bmw.motorbikefueljimcomapp.model.Owner
import com.bmw.motorbikefueljimcomapp.model.User
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_HOME
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOGIN
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_REGISTER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class OwnerRegistrationViewModel (var navController: NavHostController,var context: Context){
    var mAuth: FirebaseAuth

    init {
        mAuth= FirebaseAuth.getInstance()

    }
    fun signup(fullname: String, idNumber: String, kraPin: String, phoneNumber: String, address: String, registrationDate: String,confirmidNumber: String){


        if (fullname.isBlank()||idNumber.isBlank()||kraPin.isBlank()||phoneNumber.isBlank()||address.isBlank()||registrationDate.isBlank()){

            Toast.makeText(context,"Please firstname,lastname ,email and password can't be blank",Toast.LENGTH_LONG).show()
            return
        }else if (idNumber != confirmidNumber){
            Toast.makeText(context,"IdNumber do not match",Toast.LENGTH_LONG).show()
            return
        }else{
            mAuth.createUserWithEmailAndPassword(fullname,idNumber,).addOnCompleteListener{
                if (it.isSuccessful){
                    val ownerdata= Owner(fullname,idNumber,kraPin,phoneNumber,address,registrationDate,mAuth.currentUser!!.uid)
                    val regeRef= FirebaseDatabase.getInstance().getReference()
                        .child("Users/"+mAuth.currentUser!!.uid)
                    regeRef.setValue(ownerdata).addOnCompleteListener{
                        if(it.isSuccessful){
                            Toast.makeText(context,"${it.exception!!.message}",Toast.LENGTH_LONG).show()
                            navController.navigate(ROUTE_LOGIN)
                        }
                    }
                }else{
                    navController.navigate(ROUTE_REGISTER)
                }
            }
        }

    }
    fun login(fullname: String,idNumber: String,kraPin: String,pass: String){
        mAuth.signInWithEmailAndPassword(fullname,idNumber).addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(context,"Successful Logged In",Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_HOME)
            }else{
                Toast.makeText(context,"${it.exception!!.message}",Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN)
            }
        }
    }
    fun logout(){
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
    }
    fun isloggedin():Boolean{
        return mAuth.currentUser !=null
    }
}

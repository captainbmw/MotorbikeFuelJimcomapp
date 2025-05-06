package com.bmw.motorbikefueljimcomapp.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController

class AuthViewModelFactory (private val navController: NavHostController,
                            private val context: Context ): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(navController,context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

        }


    }

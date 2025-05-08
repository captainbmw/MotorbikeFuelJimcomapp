package com.bmw.motorbikefueljimcomapp.model



sealed class OperationStatus {
    object Loading : OperationStatus()
    data class Success(val message: String) : OperationStatus()
    data class Error(val message: String) : OperationStatus()
    object Idle:OperationStatus()
}
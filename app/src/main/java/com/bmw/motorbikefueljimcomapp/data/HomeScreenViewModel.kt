package com.bmw.motorbikefueljimcomapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmw.motorbikefueljimcomapp.data.entities.DevotionEntity

import com.bmw.motorbikefueljimcomapp.data.repository.LoanRepository
import com.bmw.motorbikefueljimcomapp.data.repository.OwnerRepository
import com.bmw.motorbikefueljimcomapp.model.OperationStatus

import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeScreenViewModel(
    private val ownerRepository: OwnerRepository,
    private val loanRepository: LoanRepository
) : ViewModel() {

    private val _todayDevotion = MutableLiveData<DevotionEntity?>()
    val todayDevotion: LiveData<DevotionEntity?> = _todayDevotion

    private val _activeOwnerCount = MutableLiveData<Int>(0)
    val activeOwnerCount: LiveData<Int> = _activeOwnerCount

    private val _activeLoanCount = MutableLiveData<Int>(0)
    val activeLoanCount: LiveData<Int> = _activeLoanCount

    private val _operationStatus = MutableLiveData<OperationStatus>()
    val operationStatus: LiveData<OperationStatus> = _operationStatus

    init {

        loadActiveOwnerCount()
        loadActiveLoanCount()
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }



    private fun loadActiveOwnerCount() {
        viewModelScope.launch {
            ownerRepository.allActiveOwners.collect { owners ->
                _activeOwnerCount.value = owners.size
            }
        }
    }

    private fun loadActiveLoanCount() {
        viewModelScope.launch {
            loanRepository.allActiveLoans.collect { loans ->
                _activeLoanCount.value = loans.size
            }
        }
    }
}
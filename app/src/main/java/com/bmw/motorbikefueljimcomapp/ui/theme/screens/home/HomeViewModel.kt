package com.bmw.motorbikefueljimcomapp.ui.theme.screens.home




import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.bmw.motorbikefueljimcomapp.data.OwnerRepository
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val context: Context) : ViewModel() {

    private val ownersRepository = OwnerRepository(context)
//    private val motorbikesRepository = MotorbikeREpository(context)
//    private val loansRepository = LoanRepository(context)

    private val _ownerCount = MutableLiveData<Int>()
    val ownerCount: LiveData<Int> = _ownerCount

    private val _motorbikeCount = MutableLiveData<Int>()
    val motorbikeCount: LiveData<Int> = _motorbikeCount

    private val _loanCount = MutableLiveData<Int>()
    val loanCount: LiveData<Int> = _loanCount
    init {
        loadCounts()
    }

    private fun loadCounts() {
        viewModelScope.launch {
            val owners = ownersRepository.getAllOwners()
            _ownerCount.value = owners.size

//            val motorbikes = motorbikesRepository.getAllMotorbikes()
//            _motorbikeCount.value = motorbikes.size
//
//            val loans = loansRepository.getAllLoans()
//            _loanCount.value = loans.size
        }
    }
}


package com.bmw.motorbikefueljimcomapp.data.repository

import com.bmw.motorbikefueljimcomapp.data.Dao.LoanDao
import com.bmw.motorbikefueljimcomapp.data.entities.LoanEntity
import kotlinx.coroutines.flow.Flow


class LoanRepository(private val loanDao: LoanDao) {
    val allActiveLoans: Flow<List<LoanEntity>> = loanDao.getAllActiveLoans()

    fun getLoansByOwner(ownerId: Int): Flow<List<LoanEntity>> {
        return loanDao.getLoansByOwner(ownerId)
    }

    suspend fun insertLoan(loan: LoanEntity): Long {
        return loanDao.insertLoan(loan)
    }

    suspend fun updateLoan(loan: LoanEntity) {
        loanDao.updateLoan(loan)
    }

    suspend fun getLoanByNumber(loanNumber: String): LoanEntity? {
        return loanDao.getLoanByNumber(loanNumber)
    }

    suspend fun updateLoanStatus(loanId: Int, status: String) {
        loanDao.updateLoanStatus(loanId, status)
    }
}
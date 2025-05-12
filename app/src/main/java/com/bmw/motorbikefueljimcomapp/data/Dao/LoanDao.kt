package com.bmw.motorbikefueljimcomapp.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bmw.motorbikefueljimcomapp.data.entities.LoanEntity
import kotlinx.coroutines.flow.Flow

// LoanDao.kt
@Dao
interface LoanDao {
    @Insert
    suspend fun insertLoan(loan: LoanEntity): Long

    @Update
    suspend fun updateLoan(loan: LoanEntity)

    @Query("SELECT * FROM loans WHERE ownerId = :ownerId AND status = 'Active'")
    fun getLoansByOwner(ownerId: Int): Flow<List<LoanEntity>>

    @Query("SELECT * FROM loans WHERE status = 'Active'")
    fun getAllActiveLoans(): Flow<List<LoanEntity>>

    @Query("SELECT * FROM loans WHERE loanNumber = :loanNumber")
    suspend fun getLoanByNumber(loanNumber: String): LoanEntity?

    @Query("UPDATE loans SET status = :status WHERE id = :loanId")
    suspend fun updateLoanStatus(loanId: Int, status: String)
}
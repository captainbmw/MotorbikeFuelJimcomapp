package com.bmw.motorbikefueljimcomapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "loans", foreignKeys = [
    ForeignKey(
        entity = OwnerEntity::class,
        parentColumns = ["id"],
        childColumns = ["ownerId"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = MotorbikeEntity::class,
        parentColumns = ["id"],
        childColumns = ["motorbikeId"],
        onDelete = ForeignKey.CASCADE
    )
])


data class LoanEntity(
    var id: String = "",
    val ownerId: String = "",
    val motorbikeId: String = "",
    val loanNumber: String = "",
    val principalAmount: Double = 0.0,
    val interestRate: Double = 0.0,
    val serviceFee: Double = 0.0,
    val totalPayable: Double = 0.0,
    val issueDate: Long = System.currentTimeMillis(),
    val dueDate: Long = System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000, // 30 days
    val repaymentDate: Long? = null,
    val status: String = "Active",
    val guarantor1Id: String? = null,
    val guarantor2Id: String? = null
)
package com.bmw.motorbikefueljimcomapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "motorbikes", foreignKeys = [
    ForeignKey(
        entity = OwnerEntity::class,
        parentColumns = ["id"],
        childColumns = ["ownerId"],
        onDelete = ForeignKey.CASCADE
    )
])
data class MotorbikeEntity(
    var id: String = "",
    val ownerId: String = "",
    val regNumber: String = "",
    val model: String = "",
    val type: String = "",
    val fuelType: String = "Petrol",
    val workStation: String = "",
    val insuranceCompany: String = "",
    val insuranceType: String = "Third Party",
    val insuranceExpiry: String,
    val status: String = "Active",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val brand: String,
    val year: Int,
    val color: String
)


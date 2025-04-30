package com.bmw.motorbikefueljimcomapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "owners")
data class OwnerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    @ColumnInfo(name = "id_number") val idNumber: String,
    val kraPin: String,
    val phoneNumber: String,
    val address: String,
    val registrationDate: String,
    val status: String = "Active"
)
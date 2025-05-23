package com.bmw.motorbikefueljimcomapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "owners")
data class OwnerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "fullName") val fullName: String,
    @ColumnInfo(name = "idNumber") val idNumber: String,
    @ColumnInfo(name = "kraPin") val kraPin: String,
    @ColumnInfo(name = "phoneNumber") val phoneNumber: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "registrationDate") val registrationDate: String,
    @ColumnInfo(name = "status") val status: String
)
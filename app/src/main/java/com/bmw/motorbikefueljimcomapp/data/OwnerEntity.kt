package com.bmw.motorbikefueljimcomapp.data



import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "owners")
data class OwnerEntity(
    @PrimaryKey(autoGenerate = true) val id: String,
    @ColumnInfo(name = "idNumber") val idNumber: String,
    @ColumnInfo(name = "status") val status: String
)
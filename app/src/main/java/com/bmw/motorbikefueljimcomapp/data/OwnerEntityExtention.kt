package com.bmw.motorbikefueljimcomapp.data

import android.R.attr.phoneNumber
import com.bmw.motorbikefueljimcomapp.data.entities.OwnerEntity

fun OwnerEntity.toOwner(): Owner{
    return Owner(
        id = id,
        idNumber= idNumber,
        status= status
    )

}

fun Owner.toOwnerEntity(): com.bmw.motorbikefueljimcomapp.data.OwnerEntity {

    return OwnerEntity(
        id= id,
        idNumber=idNumber,
        status = status


    )
}
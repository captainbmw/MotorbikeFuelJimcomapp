package com.bmw.motorbikefueljimcomapp.data

import com.bmw.motorbikefueljimcomapp.data.entities.OwnerEntity

fun OwnerEntity.toOwner(): Owner{
    return Owner(
        id = id.toString(),
        idNumber = idNumber,
        status = status,
        pass = TODO(),
        uid = TODO()
    )

}

fun Owner.toOwnerEntity(): com.bmw.motorbikefueljimcomapp.data.OwnerEntity {

    return OwnerEntity(
        id = id,
        idNumber =idNumber,
        status = status


    )
}
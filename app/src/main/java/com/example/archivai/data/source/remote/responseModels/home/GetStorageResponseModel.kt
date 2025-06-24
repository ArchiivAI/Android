package com.example.archivai.data.source.remote.responseModels.home

import com.example.archivai.domain.entities.StorageUsed

data class GetStorageResponseModel(
    val id: Int,
    val totalStorage: Int,
    val usedStorage: Int
){
    fun toStorageResponse(): StorageUsed{
        return StorageUsed(
            totalStorage = totalStorage,
            usedStorage = usedStorage
        )
    }

}

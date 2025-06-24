package com.example.archivai.domain.usecases.home

import com.example.archivai.domain.entities.StorageUsed
import com.example.archivai.domain.repository.home.HomeRepository
import javax.inject.Inject

class GetStorageUseCase @Inject constructor(
    private val repository: HomeRepository
) {

    suspend operator fun invoke(): StorageUsed =
         repository.getStorageUsed()

}
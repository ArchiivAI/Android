package com.example.archivai.domain.usecases.home

import com.example.archivai.domain.repository.home.HomeRepository
import javax.inject.Inject

class GetDocsUseCase @Inject constructor(
    private val repository: HomeRepository
) {

    suspend operator fun invoke() =
        repository.getDocs()

}
package com.example.archivai.domain.usecases.home

import com.example.archivai.domain.repository.home.HomeRepository
import javax.inject.Inject

class GetUserDetails @Inject constructor(private val homeRepository: HomeRepository) {

    suspend operator fun invoke() = homeRepository.getUserDetails()
}
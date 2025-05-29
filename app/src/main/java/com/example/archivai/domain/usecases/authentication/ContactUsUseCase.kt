package com.example.archivai.domain.usecases.authentication


import com.example.archivai.domain.repository.auth.AuthRepository
import javax.inject.Inject


class ContactUsUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email : String){
        repository.contactUS(email)
    }


}
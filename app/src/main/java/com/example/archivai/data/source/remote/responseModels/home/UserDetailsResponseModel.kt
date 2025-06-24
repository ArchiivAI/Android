package com.example.archivai.data.source.remote.responseModels.home

import com.example.archivai.domain.entities.UserDetails

data class UserDetailsResponseModel(
    val braintreeCustomerId: Any,
    val braintreePaymentToken: Any,
    val email: String,
    val firstName: String,
    val id: Int,
    val imageUrl: String,
    val lastName: String,
    val passwordHashed: String,
    val position: Position
){
    fun toUserDetails() : UserDetails{
        return UserDetails(
            email = email,
            firstName = firstName,
            id = id,
            imageUrl = imageUrl,
            lastName = lastName
        )
    }
}
package com.reqres.users.presentation

class UserModel(
    val avatar: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String
) {
    val fullName: String = "$firstName $lastName"
}
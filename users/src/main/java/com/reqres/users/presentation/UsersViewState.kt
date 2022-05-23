package com.reqres.users.presentation

sealed class UsersViewState {
    object Idle : UsersViewState()
    object Loading : UsersViewState()
    data class Completed(val users: List<UserModel>) : UsersViewState()
    data class Error(val exception: Throwable) : UsersViewState()
}
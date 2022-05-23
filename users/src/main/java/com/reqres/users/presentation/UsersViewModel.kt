package com.reqres.users.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reqres.remote.domain.UseCase
import com.reqres.users.domain.GetUsersUseCase
import com.reqres.users.domain.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class UsersViewModel @Inject constructor(
    private val getUsersUseCase: @JvmSuppressWildcards(true) UseCase<GetUsersUseCase.Params, List<UserEntity>>
) : ViewModel() {

    private val _state = MutableLiveData<UsersViewState>(UsersViewState.Idle)
    val state: LiveData<UsersViewState> = _state

    fun getUsers(page: Int) {
        viewModelScope.launch {
            _state.value = UsersViewState.Loading

            val params = GetUsersUseCase.Params(page = page)
            getUsersUseCase(params).subscribe({ users ->
                _state.value = UsersViewState.Completed(users.toUserModelEntities())
            }, { exception ->
                _state.value = UsersViewState.Error(exception)
            })
        }
    }
}
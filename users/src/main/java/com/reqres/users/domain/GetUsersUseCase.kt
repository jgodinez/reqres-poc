package com.reqres.users.domain

import com.reqres.remote.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository,
    background: CoroutineDispatcher
) : UseCase<GetUsersUseCase.Params, List<@JvmSuppressWildcards UserEntity>>(background) {

    companion object {
        // Max items allowed by reqres api
        private const val ITEMS_PER_PAGE = 12
    }

    override suspend fun run(input: Params?): List<UserEntity> {
        requireNotNull(input) { "Page must not be null." }
        return repository.loadUsers(input.page, ITEMS_PER_PAGE)
    }

    data class Params(
        val page: Int
    )
}
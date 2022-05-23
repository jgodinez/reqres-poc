package com.reqres.remote.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in Input, Output>(private val background: CoroutineDispatcher) {
    protected abstract suspend fun run(input: Input? = null): Output

    suspend operator fun invoke(input: Input? = null): Response<Output> {
        return withContext(background) {
            try {
                Response.Success(run(input))
            } catch (error: Exception) {
                Response.Failure(error)
            }
        }
    }
}
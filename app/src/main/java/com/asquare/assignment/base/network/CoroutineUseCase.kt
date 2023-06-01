package com.asquare.assignment.base.network

interface CoroutineUseCase<Params, Result> {
    suspend fun run(params: Params): Result
}

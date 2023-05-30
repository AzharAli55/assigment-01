package com.asquare.assignment.utils.networkutils

interface CoroutineUseCase<Params, Result> {
    suspend fun run(params: Params): Result
}

package com.asquare.assignment.home.api.client

import com.asquare.assignment.models.BaseResponse
import com.asquare.assignment.models.catsbreed.CatBreedsResponse
import retrofit2.Response
import retrofit2.http.Query


interface ICatApi {
    suspend fun getCatsBreed(limit: Int, page: Int): Response<CatBreedsResponse>
}
package com.asquare.assignment.home.data.remote

import com.asquare.assignment.home.domain.models.catsbreed.CatBreedsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CatApiInterface {

    @GET("/breeds")
    suspend fun getCatBreeds(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Response<CatBreedsResponse>

}
package com.asquare.assignment.home.api.client

import com.asquare.assignment.models.BaseResponse
import com.asquare.assignment.models.catsbreed.CatBreedsResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface CatApiInterface {

    @GET("/breeds")
    suspend fun getCatBreeds(@Query("limit") limit: Int,@Query("page") page: Int): Response<CatBreedsResponse>

}
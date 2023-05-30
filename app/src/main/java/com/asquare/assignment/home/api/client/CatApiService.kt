package com.asquare.assignment.home.api.client


import com.asquare.assignment.models.catsbreed.CatBreedsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject


class CatApiService @Inject constructor(val apiService: CatApiInterface) : ICatApi {


    override suspend fun getCatsBreed(limit: Int, page: Int): Response<CatBreedsResponse> {
        return withContext(Dispatchers.IO) {
            apiService.getCatBreeds(limit,page)
        }
    }
}
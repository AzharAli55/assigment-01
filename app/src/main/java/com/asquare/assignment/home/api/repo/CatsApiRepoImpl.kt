package com.asquare.assignment.home.api.repo


import com.asquare.assignment.home.api.client.ICatApi
import com.asquare.assignment.models.catsbreed.CatBreedsResponse
import com.theentertainerme.productsbl.api.repo.ICatApiRepo
import javax.inject.Inject

class CatsApiRepoImpl @Inject constructor(private val api: ICatApi) : ICatApiRepo {

    override suspend fun getCatBreedResponse(limit: Int, page: Int): CatBreedsResponse {
        val response = api.getCatsBreed(limit,page)
        return if (response.isSuccessful) requireNotNull(response.body()) else error("")
    }
}
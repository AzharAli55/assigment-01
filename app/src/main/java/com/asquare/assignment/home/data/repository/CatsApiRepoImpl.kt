package com.asquare.assignment.home.data.repository


import com.asquare.assignment.home.data.remote.CatApiInterface
import com.asquare.assignment.home.domain.models.catsbreed.CatBreedsResponse
import com.asquare.assignment.home.domain.remote.ICatApiRepo
import javax.inject.Inject

class CatsApiRepoImpl @Inject constructor(val apiService: CatApiInterface) : ICatApiRepo {

    override suspend fun getCatBreedResponse(limit: Int, page: Int): CatBreedsResponse {
        val response = apiService.getCatBreeds(limit, page)
        return if (response.isSuccessful) requireNotNull(response.body()) else error("")
    }
}
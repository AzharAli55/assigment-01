package com.asquare.assignment.home.domain.remote

import com.asquare.assignment.home.domain.models.catsbreed.CatBreedsResponse


interface ICatApiRepo {
    suspend fun getCatBreedResponse(limit: Int, page: Int): CatBreedsResponse
}
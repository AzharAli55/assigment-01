package com.theentertainerme.productsbl.api.repo

import com.asquare.assignment.models.catsbreed.CatBreedsResponse


interface ICatApiRepo {
    suspend fun getCatBreedResponse(limit: Int, page: Int): CatBreedsResponse
}
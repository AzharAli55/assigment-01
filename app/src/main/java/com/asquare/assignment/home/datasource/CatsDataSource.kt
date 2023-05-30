package com.asquare.assignment.home.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.asquare.assignment.models.catsbreed.CatBreedsResponse
import com.asquare.assignment.models.catsbreed.CatItem
import com.asquare.assignment.utils.AppLogger
import com.google.gson.Gson
import com.theentertainerme.productsbl.api.repo.ICatApiRepo

class CatsDataSource(private val iCatsApiRepo: ICatApiRepo): PagingSource<Int, CatItem>() {
    var query:String?=null
    override fun getRefreshKey(state: PagingState<Int, CatItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatItem> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        return try {
            val response = iCatsApiRepo.getCatBreedResponse(page = position, limit = params.loadSize).data?.filter { it.breed?.contains(query?:"")==true }
            LoadResult.Page(
                data = response ?: emptyList(),
                prevKey = null,
                nextKey = if (response?.isEmpty()==true) null else position.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val INITIAL_LOAD_SIZE = 1
    }
}
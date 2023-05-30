package com.theentertainerme.productsbl.api.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import com.asquare.assignment.home.datasource.CatsDataSource
import com.asquare.assignment.models.catsbreed.CatItem
import com.asquare.assignment.utils.networkutils.CoroutineUseCase
import com.google.gson.Gson
import com.theentertainerme.productsbl.api.repo.ICatApiRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCatsFactsApiUseCase @Inject constructor(private val repo: ICatApiRepo) : CoroutineUseCase<HashMap<String, String>, Flow<PagingData<CatItem>>> {

    var query:String?=null

    override suspend fun run(params: HashMap<String, String>):
            Flow<PagingData<CatItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 10,
                enablePlaceholders = false,
                maxSize = 500
            ),
            pagingSourceFactory = { CatsDataSource(repo) }
        ).flow
    }

    fun search(text: String) {
        this.query = text
    }
}
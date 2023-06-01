package com.theentertainerme.productsbl.api.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.asquare.assignment.base.network.CoroutineUseCase
import com.asquare.assignment.home.data.datasource.CatsDataSource
import com.asquare.assignment.home.domain.models.catsbreed.CatItem
import com.asquare.assignment.home.domain.remote.ICatApiRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatsFactsApiUseCase @Inject constructor(private val repo: ICatApiRepo) :
    CoroutineUseCase<HashMap<String, String>, Flow<PagingData<CatItem>>> {
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
}
package com.asquare.assignment.home.ui.uistates

import androidx.paging.PagingData
import com.asquare.assignment.models.catsbreed.CatItem
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.Flow

sealed class CatsUiStates {
    data class GetCats(val catsBreedsData:PagingData<CatItem>) : CatsUiStates()
    data class SearchResult(val query:String) : CatsUiStates()
    data class NoResults(val message: String?) : CatsUiStates()
    data class ErrorMessage(val message: String?="") : CatsUiStates()
}
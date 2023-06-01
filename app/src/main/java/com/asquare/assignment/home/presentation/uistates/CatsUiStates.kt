package com.asquare.assignment.home.presentation.uistates

import androidx.paging.PagingData
import com.asquare.assignment.home.domain.models.catsbreed.CatItem

sealed class CatsUiStates {
    data class GetCats(val catsBreedsData: PagingData<CatItem>) : CatsUiStates()
    data class SearchResult(val query: String) : CatsUiStates()
    data class NoResults(val message: String?) : CatsUiStates()
    data class ErrorMessage(val message: String? = "") : CatsUiStates()
}
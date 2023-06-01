package com.asquare.assignment.home.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asquare.assignment.home.presentation.uistates.CatsUiStates


class HomeViewModel : ViewModel() {

    private val _homeUiState = MutableLiveData<CatsUiStates>()
    val homeUiState: LiveData<CatsUiStates> get() = _homeUiState

    fun search(query: String) {
        _homeUiState.value = CatsUiStates.SearchResult(query)
    }


    fun getImagesData(): List<String> {
        val arrayList = arrayListOf<String>()
        arrayList.add("https://images6.alphacoders.com/337/thumb-1920-337780.jpg")
        arrayList.add("https://cdn.pixabay.com/photo/2017/02/20/18/03/cat-2083492_1280.jpg")
        arrayList.add("https://swall.teahub.io/photos/small/216-2166046_indian-cat-images-hd.jpg")
        return arrayList
    }
}
package com.asquare.assignment.home.viewmodels

import android.text.TextUtils
import android.util.Log
import androidx.annotation.Keep
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.asquare.assignment.home.ui.uistates.CatsUiStates
import com.asquare.assignment.models.catsbreed.CatItem
import com.theentertainerme.productsbl.api.usecase.GetCatsFactsApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@Keep
@HiltViewModel
class CatsViewModel @Inject constructor(
    private val getCatsFactsApiUseCase: GetCatsFactsApiUseCase,
) : ViewModel() {

    private val _catsBreedState = MutableLiveData<CatsUiStates>()
    val catsBreedState: LiveData<CatsUiStates> get() = _catsBreedState

    var catsBreedsData:PagingData<CatItem>?=null

    var query:String?=null

    private var catsJob: Job? = null

    fun getCatsBreeds() {
        if (catsJob?.isActive == true) catsJob?.cancel()
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            if (throwable !is CancellationException){
                throwable.printStackTrace()
            }
        }

        catsJob = viewModelScope.launch(exceptionHandler) {
            val params = hashMapOf<String, String>()
            val response = getCatsFactsApiUseCase.run(params).cachedIn(viewModelScope)
            response.collectLatest {
                catsBreedsData = it
                getFilteredResults(query?:"")?.let {
                    _catsBreedState.value = CatsUiStates.GetCats(it)
                }

            }
        }
    }

    fun search(query:String?){
        this.query=query
        getFilteredResults(query?:"")?.let {
            _catsBreedState.value = CatsUiStates.GetCats(it)
        }
    }


    private fun getFilteredResults(query:String):PagingData<CatItem>?{
        if (TextUtils.isEmpty(query))return catsBreedsData
        return catsBreedsData?.filter { it.breed.equals(query,true)
                || it.country?.equals(query,true)==true
                || it.breed?.startsWith(query?:"",true)==true
                || it.country?.startsWith(query?:"",true)==true
        }
    }




}
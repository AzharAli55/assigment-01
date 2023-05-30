package com.asquare.assignment.home.ui.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.asquare.assignment.R
import com.asquare.assignment.base.ui.BaseFragment
import com.asquare.assignment.databinding.FragmentCatListBinding
import com.asquare.assignment.home.ui.adapters.CatsAdapter
import com.asquare.assignment.home.ui.uistates.CatsUiStates
import com.asquare.assignment.home.viewmodels.CatsViewModel
import com.asquare.assignment.home.viewmodels.HomeViewModel
import com.asquare.assignment.utils.AppLogger
import com.asquare.assignment.utils.custom.RecyclerVerticalItemDecorator
import com.asquare.assignment.utils.gone
import com.asquare.assignment.utils.isInternetAvailable
import com.asquare.assignment.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatListFragment  : BaseFragment<FragmentCatListBinding>() {
    private val catsViewModel: CatsViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    private val catsAdapter: CatsAdapter by lazy {
        CatsAdapter()
    }


    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentCatListBinding.inflate(inflater, container, false)


    override fun getContainerView() = mBinding?.rvCats

    override fun getErrorView() = mBinding?.includerErrorMessage?.root


    override fun getTryAgainButton()=  mBinding?.includerErrorMessage?.btnRetry



    override fun setupViews() {
        setAdapter()
        getCats()

        mBinding?.swipeRefresh?.setOnRefreshListener { catsAdapter.refresh() }

    }

    private fun getCats(){
        if (requireActivity().isInternetAvailable()){
            mBinding?.swipeRefresh?.isRefreshing=true
            showContent()
            catsViewModel.getCatsBreeds()
        }
        else{
            mBinding?.swipeRefresh?.isRefreshing=false
            showErrorMessage(getString(R.string.message_internet_connection))
        }
    }

    override fun onTryAgain() {
        super.onTryAgain()
        getCats()
    }


    override fun showErrorMessage(message: String) {
        super.showErrorMessage(message)
        mBinding?.includerErrorMessage?.apply {
            tvNoRecord.text = message
        }
    }


    private fun setAdapter(){
        mBinding?.rvCats?.addItemDecoration(RecyclerVerticalItemDecorator(requireActivity(), R.drawable.item_divider))
        mBinding?.rvCats?.adapter=catsAdapter
    }

    override fun observeUiStates() {
        super.observeUiStates()


        homeViewModel.homeUiState.observe(this, Observer {
            when(it){
                is CatsUiStates.SearchResult->{
                    catsViewModel.search(it.query)
                }
                else->{

                }
            }
        })
        catsViewModel.catsBreedState.observe(this, Observer {
            when(it){
                is CatsUiStates.GetCats -> {
                    mBinding?.swipeRefresh?.isRefreshing=false
                    AppLogger.logDebug("CatsUiStates.GetCats")
                   catsViewModel.viewModelScope.launch {
                       catsAdapter.submitData(it.catsBreedsData)
                   }
                }
                else->{

                }
            }
        })
    }

    companion object{
        fun newInstance()= CatListFragment().apply {

        }
    }
}
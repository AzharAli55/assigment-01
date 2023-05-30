package com.asquare.assignment.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.asquare.assignment.utils.gone
import com.asquare.assignment.utils.visible

abstract class BaseFragment<VB : ViewBinding> : Fragment(), LifecycleEventObserver {

    private var _binding: VB? = null
    protected val mBinding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = setupViewBinding(inflater, container)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(this)
        observeUiStates()
        setupViews()
        handleClicks()
    }

    open fun getContainerView(): View? = null

    open fun getErrorView(): View? = null

    open fun getTryAgainButton(): View? = null


    abstract fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun setupViews()

    protected open fun handleClicks() {
        getTryAgainButton()?.setOnClickListener {
            onTryAgain()
        }
    }

    protected open fun onTryAgain(){

    }



    protected open fun observeUiStates() {}


    protected open fun showErrorMessage(message:String) {
        getContainerView()?.gone()
        getErrorView()?.visible()
    }


    protected open fun showContent() {
        getErrorView()?.gone()
        getContainerView()?.visible()
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            _binding = null
            viewLifecycleOwner.lifecycle.removeObserver(this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun getStringFromBundle(key:String):String?{
        return arguments?.getString(key)
    }

}
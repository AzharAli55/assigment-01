package com.asquare.assignment.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), LifecycleEventObserver {

    private var _binding: VB? = null
    protected val mBinding get() = requireNotNull(_binding)

    abstract fun setupViewBinding(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = setupViewBinding(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        lifecycle.addObserver(this)
        setupUI()
    }


    abstract fun setupUI()

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            _binding = null
            lifecycle.removeObserver(this)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
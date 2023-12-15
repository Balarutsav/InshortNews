package com.utsav.inshortnews.ui.base

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController


abstract class BaseFragment<B : ViewDataBinding?> : Fragment() {
    var TAG = "BaseFragment"
    lateinit var activity: Activity
    var binding: B? = null

    @get:LayoutRes
    protected abstract val layoutResourceId: Int


    protected abstract fun setupViews()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Log.e(TAG, "onCreateView: ")
        activity = requireActivity()
        if (binding == null) {
            binding = DataBindingUtil.inflate(
                inflater, layoutResourceId, container, false
            )
            setupViews()
        }
        return binding!!.root
    }
    protected fun navigateToDestination(destinationId: NavDirections) {
        findNavController().navigate(destinationId)
    }

}
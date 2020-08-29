package com.rtr.itunesearch.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.rtr.itunesearch.R
import com.rtr.itunesearch.databinding.FragmentBaseBinding

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Base fragment class for implementing common features of Fragment classes
 */
open class BaseFragment : Fragment() {

    private lateinit var fragmentBaseBinding : FragmentBaseBinding
    private lateinit var fragmentViewModel : BaseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBaseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)
        return fragmentBaseBinding.root
    }

    /**
     * Method to set content view for the fragments
     */
    fun setContentViewForFragment(view : View, viewModel: BaseViewModel) {
        fragmentBaseBinding.viewModel = viewModel
        fragmentViewModel = viewModel
        fragmentBaseBinding.layoutBaseFragmentContent.addView(view)
    }
}
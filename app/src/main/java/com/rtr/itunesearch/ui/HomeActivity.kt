package com.rtr.itunesearch.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rtr.itunesearch.R
import com.rtr.itunesearch.base.BaseActivity
import com.rtr.itunesearch.databinding.ActivityHomeBinding
import com.rtr.itunesearch.model.MusicResponseModel
import com.rtr.itunesearch.model.TrackDataModel
import com.rtr.itunesearch.utils.SortOrder
import com.rtr.itunesearch.viewmodel.HomeViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Activity class for Home screen
 */
class HomeActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = setContentViewForActivity(R.layout.activity_home, viewModel) as ActivityHomeBinding
        binding.viewModel = viewModel
        addObservers()
        setAdapterForSorting()
        viewModel.getMusicListData()
    }

    /**
     * Method to set adapter for sorting spinner
     */
    private fun setAdapterForSorting(){
        ArrayAdapter.createFromResource(this, R.array.array_sort_album,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerSorting.adapter = adapter
        }
        binding.spinnerSorting.onItemSelectedListener = this
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        /**/
    }

    override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, p3: Long) {
        viewModel.sortOrder = when(position){
            0 -> SortOrder.RELEASE_DATE
            1 -> SortOrder.COLLECTION
            2 -> SortOrder.TRACK
            3 -> SortOrder.ARTIST
            4 -> SortOrder.PRICE_LOW_HIGH
            5 -> SortOrder.PRICE_HIGH_LOW
            else -> SortOrder.RELEASE_DATE
        }
        viewModel.getSortedList(viewModel.sortOrder, binding.searchViewEmployee.query?.toString() ?: "")
    }

    /**
     * Method to add observers
     */
    private fun addObservers(){
        viewModel.adapterMusicList.observe(this, Observer<MutableList<TrackDataModel>> {
            if(it != null && it.size > 0) {
                viewModel.isDataEmpty.set(false)
                setAdapterForEmployeeListing(it)
            }else viewModel.isDataEmpty.set(true)
        })
        binding.searchViewEmployee.setOnQueryTextListener(searchListener)
    }

    /**
     * Method to set adapter
     */
    private fun setAdapterForEmployeeListing(list : MutableList<TrackDataModel>){
        binding.recyclerViewEmployee.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = TrackRecyclerViewAdapter(list)
        }
    }

    /**
     * Listener for search
     */
    private val searchListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }
        override fun onQueryTextChange(newText: String?): Boolean {
            viewModel.getSortedList(viewModel.sortOrder, newText)
            return true
        }
    }
}
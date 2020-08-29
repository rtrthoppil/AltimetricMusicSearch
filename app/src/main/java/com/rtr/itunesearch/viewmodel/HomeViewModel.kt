package com.rtr.itunesearch.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.rtr.itunesearch.utils.MusicDataSource
import com.rtr.itunesearch.base.BaseViewModel
import com.rtr.itunesearch.model.MusicResponseModel
import com.rtr.itunesearch.model.TrackDataModel
import com.rtr.itunesearch.utils.AppConstUtils.FILE_NAME_JSON_RESPONSE
import com.rtr.itunesearch.utils.SortOrder
import java.util.*

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * View model for home screen
 */
class HomeViewModel(var app: Application) : BaseViewModel(app) {

    var trackListFull : MutableList<TrackDataModel>? = mutableListOf()
    var adapterMusicList : MutableLiveData<MutableList<TrackDataModel>> = MutableLiveData()
    var isDataEmpty : ObservableBoolean = ObservableBoolean(true)
    var musicResponseModel : MusicResponseModel? = null
    var dataSource : MusicDataSource? = null
    var sortOrder : SortOrder = SortOrder.RELEASE_DATE

    init {
        isDataEmpty.set(false)
    }

    /**
     * Method to get music list data
     */
    fun getMusicListData(){
        musicResponseModel = readDataFromAssets()
        dataSource =
            MusicDataSource(musicResponseModel)
        trackListFull = dataSource?.getAllListing()
        getSortedList(searchHint = null)
    }

    /**
     * Method to read data from asset
     */
    private fun readDataFromAssets() : MusicResponseModel?{
        val responseString : String? = app.assets.open(FILE_NAME_JSON_RESPONSE).bufferedReader().use{
            it.readText()
        } ?: ""
        return Gson().fromJson(responseString, MusicResponseModel::class.java)
    }

    /**
     * Method to get sorted list
     */
    fun getSortedList(sortOrder : SortOrder = SortOrder.RELEASE_DATE, searchHint : String?){
        adapterMusicList.value = when(sortOrder){
            SortOrder.RELEASE_DATE -> getListInReleaseDateOrder(searchHint)
            SortOrder.COLLECTION -> getListInCollectionOrder(searchHint)
            SortOrder.TRACK -> getListInTrackOrder(searchHint)
            SortOrder.ARTIST -> getListInArtistOrder(searchHint)
            SortOrder.PRICE_LOW_HIGH -> getListInPriceLowOrder(searchHint)
            SortOrder.PRICE_HIGH_LOW -> getListInPriceHighOrder(searchHint)
            else -> getListInReleaseDateOrder(searchHint)
        }
    }

    /**
     * Method to search list
     */
    private fun getSearchResult(searchHint : String) : MutableList<TrackDataModel>?{
       return trackListFull?.filter {
            (it.trackName.get() ?: "").toLowerCase(Locale.getDefault()).contains(searchHint.toLowerCase(Locale.getDefault())) ||
                    (it.artistName.get() ?: "").toLowerCase(Locale.getDefault()).contains(searchHint.toLowerCase(Locale.getDefault())) ||
                    (it.collectionName.get() ?: "").toLowerCase(Locale.getDefault()).contains(searchHint.toLowerCase(Locale.getDefault()))
       } as MutableList<TrackDataModel>?
    }

    /**
     * Method to sort list in order of date
     */
    private fun getListInReleaseDateOrder(searchHint : String?) : MutableList<TrackDataModel>?{
        val list : MutableList<TrackDataModel>? = if(searchHint != null && searchHint?.length > 0)
            getSearchResult(searchHint)
        else trackListFull
        return list?.sortedBy { it.releaseDate.get() } as MutableList<TrackDataModel>?
    }

    /**
     * Method to sort list in order of collection name
     */
    private fun getListInCollectionOrder(searchHint : String?) : MutableList<TrackDataModel>?{
        val list : MutableList<TrackDataModel>? = if(searchHint != null && searchHint?.length > 0)
            getSearchResult(searchHint)
        else trackListFull
        return list?.sortedBy { it.collectionName.get() } as MutableList<TrackDataModel>?
    }

    /**
     * Method to sort list in order of track name
     */
    private fun getListInTrackOrder(searchHint : String?) : MutableList<TrackDataModel>?{
        val list : MutableList<TrackDataModel>? = if(searchHint != null && searchHint?.length > 0)
            getSearchResult(searchHint)
        else trackListFull
        return list?.sortedBy { it.trackName.get() } as MutableList<TrackDataModel>?
    }

    /**
     * Method to sort list in order of artist name
     */
    private fun getListInArtistOrder(searchHint : String?) : MutableList<TrackDataModel>?{
        val list : MutableList<TrackDataModel>? = if(searchHint != null && searchHint?.length > 0)
            getSearchResult(searchHint)
        else trackListFull
        return list?.sortedBy { it.artistName.get() } as MutableList<TrackDataModel>?
    }

    /**
     * Method to sort list in order of price
     */
    private fun getListInPriceHighOrder(searchHint : String?) : MutableList<TrackDataModel>?{
        val list : MutableList<TrackDataModel>? = if(searchHint != null && searchHint?.length > 0)
            getSearchResult(searchHint)
        else trackListFull
        list?.sortByDescending { it.price.get() ?: 0.0 }
        return list
    }

    /**
     * Method to sort list in order of price
     */
    private fun getListInPriceLowOrder(searchHint : String?) : MutableList<TrackDataModel>?{
        val list : MutableList<TrackDataModel>? = if(searchHint != null && searchHint?.length > 0)
            getSearchResult(searchHint)
        else trackListFull
        list?.sortBy { it.price.get() ?: 0.0 }
        return list
    }
}
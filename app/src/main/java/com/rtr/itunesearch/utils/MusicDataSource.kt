package com.rtr.itunesearch.utils

import androidx.databinding.ObservableField
import com.rtr.itunesearch.model.MusicResponseModel
import com.rtr.itunesearch.model.Results
import com.rtr.itunesearch.model.TrackDataModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Data source class for music listing
 */
class MusicDataSource(var response: MusicResponseModel?) {

    /**
     * Method to remove duplicate
     */
    private fun removeDuplicates(): MutableList<Results>? {
        response?.let {
            it.results?.let { list ->
                return list.distinctBy { item -> item.trackName } as MutableList<Results>?
            }
        }
        return null
    }

    /**
     * Method to get all music listing
     */
    fun getAllListing(): MutableList<TrackDataModel>? {
        val distinctList = removeDuplicates()
        val resultList = mutableListOf<TrackDataModel>()
        distinctList?.let { list ->
            for(item in list){
                resultList.add(
                    TrackDataModel(
                        trackId = item.trackId,
                        trackName = ObservableField(item.trackName ?: ""),
                        artistName = ObservableField(item.artistName ?: ""),
                        collectionName = ObservableField(item.collectionName ?: ""),
                        imageUrl = ObservableField(item.artworkUrl100 ?: ""),
                        price = ObservableField(item.trackPrice ?: 0.0),
                        releaseDate = ObservableField(DateTimeUtils.getTimeStampFromDateTime(item.releaseDate ?: ""))
                    )
                )
            }
        }
        return resultList
    }

}
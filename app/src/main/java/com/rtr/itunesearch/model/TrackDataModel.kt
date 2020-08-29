package com.rtr.itunesearch.model

import android.os.Parcelable
import android.view.View
import androidx.databinding.ObservableField
import kotlinx.android.parcel.Parcelize

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Data class for each track
 */
@Parcelize
data class TrackDataModel(
    var trackId : Int? = 0,
    var trackName : ObservableField<String> = ObservableField(""),
    var artistName : ObservableField<String> = ObservableField(""),
    var collectionName : ObservableField<String> = ObservableField(""),
    var price : ObservableField<Double> = ObservableField(0.0),
    var releaseDate : ObservableField<Long> = ObservableField(0L),
    var imageUrl : ObservableField<String> = ObservableField(""),
    var isSelected : ObservableField<Boolean> = ObservableField(false)
) : Parcelable{

    fun onClickAddToCart(view :View){
        this.isSelected.set(!(this.isSelected.get() ?: true))
    }

}
package com.rtr.itunesearch.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Model data class for itunes response
 */
@Parcelize
data class MusicResponseModel(
    @SerializedName("resultCount") var resultCount: Int?,
    @SerializedName("results") var results: List<Results>?
) : BaseResponse(), Parcelable

/**
 * Model data class for result
 */
@Parcelize
data class Results(
    @SerializedName("wrapperType") var wrapperType: String?,
    @SerializedName("kind") var kind: String?,
    @SerializedName("artistId") var artistId: Int?,
    @SerializedName("collectionId") var collectionId: Int?,
    @SerializedName("trackId") var trackId: Int?,
    @SerializedName("artistName") var artistName: String?,
    @SerializedName("collectionName") var collectionName: String?,
    @SerializedName("trackName") var trackName: String?,
    @SerializedName("collectionCensoredName") var collectionCensoredName: String?,
    @SerializedName("trackCensoredName") var trackCensoredName: String?,
    @SerializedName("collectionArtistId") var collectionArtistId: Int?,
    @SerializedName("collectionArtistName") var collectionArtistName: String?,
    @SerializedName("artistViewUrl") var artistViewUrl: String?,
    @SerializedName("collectionViewUrl") var collectionViewUrl: String?,
    @SerializedName("trackViewUrl") var trackViewUrl: String?,
    @SerializedName("previewUrl") var previewUrl: String?,
    @SerializedName("artworkUrl30") var artworkUrl30: String?,
    @SerializedName("artworkUrl60") var artworkUrl60: String?,
    @SerializedName("artworkUrl100") var artworkUrl100: String?,
    @SerializedName("collectionPrice") var collectionPrice: Double?,
    @SerializedName("trackPrice") var trackPrice: Double?,
    @SerializedName("releaseDate") var releaseDate: String?,
    @SerializedName("collectionExplicitness") var collectionExplicitness: String?,
    @SerializedName("trackExplicitness") var trackExplicitness: String?,
    @SerializedName("discCount") var discCount: Int?,
    @SerializedName("discNumber") var discNumber: Int?,
    @SerializedName("trackCount") var trackCount: Int?,
    @SerializedName("trackNumber") var trackNumber: Int?,
    @SerializedName("trackTimeMillis") var trackTimeMillis: Int?,
    @SerializedName("country") var country: String?,
    @SerializedName("currency") var currency: String?,
    @SerializedName("primaryGenreName") var primaryGenreName: String?,
    @SerializedName("isStreamable") var isStreamable: Boolean?
) : Parcelable
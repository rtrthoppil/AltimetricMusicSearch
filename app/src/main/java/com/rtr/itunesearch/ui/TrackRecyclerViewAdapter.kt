package com.rtr.itunesearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rtr.itunesearch.databinding.LayoutTrackItemBinding
import com.rtr.itunesearch.model.TrackDataModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Recycler view adapter class for listing tracks
 */
class TrackRecyclerViewAdapter(var trackList : MutableList<TrackDataModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TrackViewHolder(LayoutTrackItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = trackList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TrackViewHolder).setModel(trackList[position])
    }

    /**
     * View holder class for track listing
     */
    inner class TrackViewHolder(var binding: LayoutTrackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setModel(item: TrackDataModel) {
            binding.model = item
        }
    }
}
package com.rtr.itunesearch.base

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Base view model class for common methods related to view models
 */
open class BaseViewModel(var appContext : Application) : AndroidViewModel(appContext) {

    var showContent: ObservableBoolean = ObservableBoolean(true)
    var showProgress: ObservableBoolean = ObservableBoolean(false)

    /**
     * Method to show content view
     * @param status : Boolean value for show and hide
     */
    fun showContentView(status: Boolean) {
        showContent.set(status)
        showProgress.set(!status)
    }

    /**
     * Method to show progress view
     */
    fun showProgressView(status: Boolean) {
        showProgress.set(status)
    }
}
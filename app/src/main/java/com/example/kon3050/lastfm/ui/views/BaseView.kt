package com.example.kon3050.lastfm.ui.views

interface BaseView {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    fun showLoading()

    /**
     * Hide a loading view.
     */
    fun hideLoading()

    /**
     * Show an error message
     *
     * @param throwable
     */
    fun showError(throwable: Throwable)
}

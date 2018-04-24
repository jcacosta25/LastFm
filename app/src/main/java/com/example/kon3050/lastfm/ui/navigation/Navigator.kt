package com.example.kon3050.lastfm.ui.navigation

interface Navigator {

    fun navigateToMainActivity()

    fun navigateToDetailActivity(artistName: String)

    fun navigateToDetailScreen(artistName: String)

    fun navigateToTopArtistScreen()

    fun getNavigator(): NavigatorManager

}
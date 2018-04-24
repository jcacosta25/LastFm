package com.example.kon3050.lastfm.ui.model

data class TopArtistUiModel (
        val artistId: String,
        val artistName: String,
        val streamable: Boolean,
        val artistListeners:String,
        val image: String
) : BaseModel()

class ListTopArtistUiModel : BaseModel() {
    val artists =  mutableListOf<TopArtistUiModel>()
}
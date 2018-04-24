package com.example.kon3050.lastfm.data.domain.model

import com.example.kon3050.lastfm.data.domain.model.base.BaseModel

data class TopArtistModel (
        val artistId: String,
        val artistName: String,
        val streamable: Boolean,
        val artistListeners:String,
        val image: String
) : BaseModel()

class ListTopArtistModel : BaseModel() {
    val artists = mutableListOf<TopArtistModel>()
}
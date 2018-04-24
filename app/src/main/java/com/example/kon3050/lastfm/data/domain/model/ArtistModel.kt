package com.example.kon3050.lastfm.data.domain.model

import com.example.kon3050.lastfm.data.domain.model.base.BaseModel

data class ArtistModel (
        val artistId: String = "",
        val artistName: String = "",
        val image: String = "",
        val streamable: Boolean = false,
        val onTour: Boolean = false,
        val published: String = "",
        val summary: String = "",
        val content: String = ""
) : BaseModel()
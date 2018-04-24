package com.example.kon3050.lastfm.data.api.model

import com.google.gson.annotations.SerializedName

data class ArtistImage(
        @SerializedName("#text")
        val text: String,
        @SerializedName("size")
        val size: String
)


data class TopArtistResponse(
        @SerializedName("name")
        val name: String,
        @SerializedName("listeners")
        val listeners: String,
        @SerializedName("mbid")
        val mbid: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("streamable")
        val streamable: Boolean,
        @SerializedName("image")
        var image: List<ArtistImage>
)

data class TopArtistList(
        @SerializedName("artist")
        var artist: List<TopArtistResponse>
)

data class TopArtistWrapperResponse(
        @SerializedName("topartists")
        var topArtist: TopArtistList
)
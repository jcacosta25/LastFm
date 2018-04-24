package com.example.kon3050.lastfm.data.api.model

import com.google.gson.annotations.SerializedName

data class ArtistDetailWrapperResponse(
        @SerializedName("artist")
        val artist: ArtistDetailResponse
)

data class ArtistDetailResponse(
        @SerializedName("name")
        val name: String,
        @SerializedName("mbid")
        val mbid: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("image")
        var image: List<ArtistImage>,
        @SerializedName("streamable")
        val streamable: Boolean,
        @SerializedName("ontour")
        val ontour: Boolean,
        @SerializedName("bio")
        var bio: ArtistBio
)

data class ArtistBio(
        @SerializedName("published")
        val published: String,
        @SerializedName("summary")
        val summary: String,
        @SerializedName("content")
        val content: String
)
package com.example.kon3050.lastfm.data.api

import android.arch.lifecycle.LiveData
import com.example.kon3050.lastfm.data.api.model.ApiResponse
import com.example.kon3050.lastfm.data.api.model.ArtistDetailWrapperResponse
import com.example.kon3050.lastfm.data.api.model.TopArtistWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

public interface LastFmApi {

    @GET("?method=geo.gettopartists&format=json")
    fun getTopArtist(@Query("country") country: String): LiveData<ApiResponse<TopArtistWrapperResponse>>

    @GET("?method=artist.getinfo&format=json")
    fun getArtistInfo(@Query("artist") artist: String): LiveData<ApiResponse<ArtistDetailWrapperResponse>>
}
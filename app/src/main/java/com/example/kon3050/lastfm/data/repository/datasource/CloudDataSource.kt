package com.example.kon3050.lastfm.data.repository.datasource

import android.arch.lifecycle.LiveData
import com.example.kon3050.lastfm.data.api.model.ApiResponse
import com.example.kon3050.lastfm.data.api.model.ArtistDetailWrapperResponse
import com.example.kon3050.lastfm.data.api.model.TopArtistWrapperResponse

interface CloudDataSource {

    fun getTopArtist(country: String): LiveData<ApiResponse<TopArtistWrapperResponse>>

    fun getArtistInfo(artistName: String): LiveData<ApiResponse<ArtistDetailWrapperResponse>>
}
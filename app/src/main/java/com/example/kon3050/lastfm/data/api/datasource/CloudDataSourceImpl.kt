package com.example.kon3050.lastfm.data.api.datasource

import android.arch.lifecycle.LiveData
import com.example.kon3050.lastfm.data.api.LastFmApi
import com.example.kon3050.lastfm.data.api.model.ApiResponse
import com.example.kon3050.lastfm.data.api.model.ArtistDetailWrapperResponse
import com.example.kon3050.lastfm.data.api.model.TopArtistWrapperResponse
import com.example.kon3050.lastfm.data.repository.datasource.CloudDataSource
import javax.inject.Inject

class CloudDataSourceImpl @Inject constructor(private val api:LastFmApi) : CloudDataSource {

    override fun getTopArtist(country: String): LiveData<ApiResponse<TopArtistWrapperResponse>> {
        return api.getTopArtist(country)
    }

    override fun getArtistInfo(artistName: String): LiveData<ApiResponse<ArtistDetailWrapperResponse>> {
        return api.getArtistInfo(artistName)
    }
}
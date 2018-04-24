package com.example.kon3050.lastfm.data.domain

import android.arch.lifecycle.LiveData
import com.example.kon3050.lastfm.data.domain.model.ArtistModel
import com.example.kon3050.lastfm.data.domain.model.ListTopArtistModel

interface Repository {

    fun fetchTopArtist() : LiveData<ListTopArtistModel>

    fun selectTopArtist() : LiveData<ListTopArtistModel>

    fun fetchArtist(artistName: String, artistId: String) : LiveData<ArtistModel>

    fun insertTopArtist(listTopArtistModel: ListTopArtistModel) : List<Long>
}
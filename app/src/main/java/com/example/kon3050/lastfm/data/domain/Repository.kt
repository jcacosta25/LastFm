package com.example.kon3050.lastfm.data.domain

import android.arch.lifecycle.LiveData
import com.example.kon3050.lastfm.data.domain.model.ArtistModel
import com.example.kon3050.lastfm.data.domain.model.ListTopArtistModel

interface Repository {

    fun fetchTopArtist() : LiveData<ListTopArtistModel>

    fun selectTopArtist() : LiveData<ListTopArtistModel>

    fun insertTopArtist(listTopArtistModel: ListTopArtistModel) : List<Long>


    fun fetchArtist(artistName: String) : LiveData<ArtistModel>

    fun selectArtist(artistName:String):LiveData<ArtistModel>

    fun insertArtist(artist:ArtistModel):Long

}
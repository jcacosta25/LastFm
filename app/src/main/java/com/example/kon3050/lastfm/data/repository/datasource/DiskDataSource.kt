package com.example.kon3050.lastfm.data.repository.datasource

import android.arch.lifecycle.LiveData
import com.example.kon3050.lastfm.data.db.entity.ArtistEntity
import com.example.kon3050.lastfm.data.db.entity.TopArtistEntity

interface DiskDataSource {

    fun insertTopArtist(entity: List<TopArtistEntity>) : List<Long>

    fun insertArtist(entity: ArtistEntity) : Long

    fun selectTopArtist(artistId: String): LiveData<TopArtistEntity>

    fun selectArtist(artistId: String): LiveData<ArtistEntity>

    fun selectAllArtist(): LiveData<List<ArtistEntity>>

    fun selectAllTopArtist() : LiveData<List<TopArtistEntity>>
}
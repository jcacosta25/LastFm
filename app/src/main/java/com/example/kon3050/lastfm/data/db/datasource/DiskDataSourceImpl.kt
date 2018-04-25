package com.example.kon3050.lastfm.data.db.datasource

import android.arch.lifecycle.LiveData
import com.example.kon3050.lastfm.data.db.AppDataBase
import com.example.kon3050.lastfm.data.db.entity.ArtistEntity
import com.example.kon3050.lastfm.data.db.entity.TopArtistEntity
import com.example.kon3050.lastfm.data.repository.datasource.DiskDataSource
import javax.inject.Inject

class DiskDataSourceImpl @Inject constructor(private val database: AppDataBase) : DiskDataSource {

    override fun insertTopArtist(entity: List<TopArtistEntity>): List<Long> {
        return database.topArtistDao().insertAll(entity)
    }

    override fun insertArtist(entity: ArtistEntity): Long {
        return database.artistDao().insert(entity)
    }

    override fun selectTopArtist(artistId: String): LiveData<TopArtistEntity> {
        return database.topArtistDao().selectArtistById(artistId)
    }

    override fun selectArtist(artistName: String): LiveData<ArtistEntity> {
        return database.artistDao().selectArtist(artistName)
    }

    override fun selectAllArtist(): LiveData<List<ArtistEntity>> {
        return database.artistDao().selectAll()
    }

    override fun selectAllTopArtist(): LiveData<List<TopArtistEntity>> {
        return database.topArtistDao().selectAll()
    }
}
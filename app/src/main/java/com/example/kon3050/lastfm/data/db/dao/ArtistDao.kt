package com.example.kon3050.lastfm.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.kon3050.lastfm.data.db.dao.base.BaseDao
import com.example.kon3050.lastfm.data.db.entity.ArtistEntity
import com.example.kon3050.lastfm.data.db.entity.COLUMN_ARTIST_ID
import com.example.kon3050.lastfm.data.db.entity.TABLE_NAME_ARTIST_DETAIL

@Dao
interface ArtistDao : BaseDao<ArtistEntity> {

    @Query("select * from $TABLE_NAME_ARTIST_DETAIL where $COLUMN_ARTIST_ID = :artistId")
    fun selectArtist(artistId: String): LiveData<ArtistEntity>

    @Query("select * from $TABLE_NAME_ARTIST_DETAIL")
    fun selectAll(): LiveData<List<ArtistEntity>>
}
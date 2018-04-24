package com.example.kon3050.lastfm.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.Query
import com.example.kon3050.lastfm.data.db.dao.base.BaseDao
import com.example.kon3050.lastfm.data.db.entity.COLUMN_ARTIST_ID
import com.example.kon3050.lastfm.data.db.entity.TABLE_NAME_TOP_ARTIST
import com.example.kon3050.lastfm.data.db.entity.TopArtistEntity

@Dao

interface TopArtistDao : BaseDao<TopArtistEntity> {

    @Insert(onConflict = IGNORE)
    fun insertAll(entities: List<TopArtistEntity>): List<Long>

    @Query("select * from $TABLE_NAME_TOP_ARTIST")
    fun selectAll(): LiveData<List<TopArtistEntity>>

    @Query("select * from $TABLE_NAME_TOP_ARTIST where $COLUMN_ARTIST_ID = :artistId")
    fun selectArtistById(artistId: String): LiveData<TopArtistEntity>
}
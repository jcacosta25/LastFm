package com.example.kon3050.lastfm.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

const val TABLE_NAME_TOP_ARTIST = "top_artist"
const val COLUMN_ARTIST_ID = "artist_id"
const val COLUMN_ARTIST_NAME = "artist_name"
const val COLUMN_ARTIST_LISTENERS = "artist_listeners"
const val COLUMN_ARTIST_STREAMABLE = "artist_streamable"
const val COLUMN_ARTIST_IMAGE = "artist_image"

@Entity(tableName = TABLE_NAME_TOP_ARTIST)
data class TopArtistEntity(

        @NonNull
        @PrimaryKey
        @ColumnInfo(name = COLUMN_ARTIST_ID)
        val artistId: String,

        @ColumnInfo(name = COLUMN_ARTIST_NAME)
        val artistName: String,

        @ColumnInfo(name = COLUMN_ARTIST_LISTENERS)
        val artistListeners: String,

        @ColumnInfo(name = COLUMN_ARTIST_STREAMABLE)
        val streamable: Boolean,

        @ColumnInfo(name = COLUMN_ARTIST_IMAGE)
        val image: String
)
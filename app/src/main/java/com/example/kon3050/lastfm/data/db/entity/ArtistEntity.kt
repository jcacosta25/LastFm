package com.example.kon3050.lastfm.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

const val TABLE_NAME_ARTIST_DETAIL = "artist_detail"
const val COLUMN_ARTIST_ONTOUR = "artist_on_tour"
const val COLUMN_ARTIST_PUBLISHED = "artist_published"
const val COLUMN_ARTIST_SUMMARY = "artist_summary"
const val COLUMN_ARTIST_CONTENT = "artist_content"

@Entity(tableName = TABLE_NAME_ARTIST_DETAIL)
data class ArtistEntity(

        @NonNull
        @PrimaryKey
        @ColumnInfo(name = COLUMN_ARTIST_ID)
        val artistId: String,

        @ColumnInfo(name = COLUMN_ARTIST_NAME)
        val artistName: String,

        @ColumnInfo(name = COLUMN_ARTIST_STREAMABLE)
        val streamable: Boolean,

        @ColumnInfo(name = COLUMN_ARTIST_IMAGE)
        val image: String,

        @ColumnInfo(name = COLUMN_ARTIST_ONTOUR)
        val onTour: Boolean,

        @ColumnInfo(name = COLUMN_ARTIST_PUBLISHED)
        val published: String,

        @ColumnInfo(name = COLUMN_ARTIST_SUMMARY)
        val summary: String,

        @ColumnInfo(name = COLUMN_ARTIST_CONTENT)
        val content: String
)
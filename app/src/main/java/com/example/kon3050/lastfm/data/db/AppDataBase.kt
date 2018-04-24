package com.example.kon3050.lastfm.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.kon3050.lastfm.data.db.dao.ArtistDao
import com.example.kon3050.lastfm.data.db.dao.TopArtistDao
import com.example.kon3050.lastfm.data.db.entity.ArtistEntity
import com.example.kon3050.lastfm.data.db.entity.TopArtistEntity

const val DATABASE_NAME = "last_fm_app.db"

@Database(entities = [TopArtistEntity::class,ArtistEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun topArtistDao(): TopArtistDao
    abstract fun artistDao(): ArtistDao
}
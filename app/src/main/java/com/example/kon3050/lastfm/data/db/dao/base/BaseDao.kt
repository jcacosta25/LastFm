package com.example.kon3050.lastfm.data.db.dao.base

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE

interface BaseDao<T> {

    @Insert(onConflict = IGNORE)
    fun insert(entity: T): Long

    @Insert(onConflict = REPLACE)
    fun upsert(entity: T): Long

    @Delete
    fun delete(entity: T)
}
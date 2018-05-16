package com.example.kon3050.lastfm.data.domain.interator

import android.arch.lifecycle.LiveData
import com.example.kon3050.lastfm.AppExecutors
import com.example.kon3050.lastfm.data.domain.Repository
import com.example.kon3050.lastfm.data.domain.interator.base.NetworkBoundResource
import com.example.kon3050.lastfm.data.domain.model.ListTopArtistModel
import javax.inject.Inject

class UseCaseGetTopArtist @Inject constructor(
        private val appExecutors: AppExecutors,
        private val repository: Repository
) : NetworkBoundResource<ListTopArtistModel, ListTopArtistModel>(appExecutors) {


    init {
        val loading = ListTopArtistModel()
        loading.status = "LOADING"
        init(loading)
    }


    override fun saveCallResult(item: ListTopArtistModel) {
        repository.insertTopArtist(item)
    }

    override fun shouldFetch(data: ListTopArtistModel?): Boolean = true

    override fun loadFromDb(): LiveData<ListTopArtistModel> {
        return repository.selectTopArtist()
    }

    override fun createCall(): LiveData<ListTopArtistModel> {
        return repository.fetchTopArtist()
    }
}
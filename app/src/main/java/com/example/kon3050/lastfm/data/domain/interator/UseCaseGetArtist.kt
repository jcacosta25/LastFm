package com.example.kon3050.lastfm.data.domain.interator

import android.arch.lifecycle.LiveData
import com.example.kon3050.lastfm.AppExecutors
import com.example.kon3050.lastfm.data.domain.Repository
import com.example.kon3050.lastfm.data.domain.interator.base.NetworkBoundResource
import com.example.kon3050.lastfm.data.domain.interator.result.Resource
import com.example.kon3050.lastfm.data.domain.model.ArtistModel
import javax.inject.Inject

class UseCaseGetArtist @Inject constructor(
        appExecutors: AppExecutors,
        private val repository: Repository
) : NetworkBoundResource<ArtistModel, ArtistModel>(appExecutors) {

    var artistName: String = "The Beatles"

    fun execute(artistName: String):LiveData<Resource<ArtistModel>> {
        this.artistName = artistName
        val loading = ArtistModel()
        loading.status = "LOADING"
        init(loading)
        return asLiveData()
    }

    override fun saveCallResult(item: ArtistModel) {
        repository.insertArtist(item)
    }

    override fun shouldFetch(data: ArtistModel?): Boolean {
        return data == null || data.artistId.isNullOrEmpty()
    }

    override fun loadFromDb(): LiveData<ArtistModel> {
        return repository.selectArtist(artistName)
    }

    override fun createCall(): LiveData<ArtistModel> {
        return repository.fetchArtist(artistName)
    }
}

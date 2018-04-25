package com.example.kon3050.lastfm.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Transformations
import com.example.kon3050.lastfm.AppExecutors
import com.example.kon3050.lastfm.data.domain.Repository
import com.example.kon3050.lastfm.data.domain.model.ArtistModel
import com.example.kon3050.lastfm.data.domain.model.ListTopArtistModel
import com.example.kon3050.lastfm.data.mapper.ArtistMapper
import com.example.kon3050.lastfm.data.repository.datasource.CloudDataSource
import com.example.kon3050.lastfm.data.repository.datasource.DiskDataSource
import com.example.kon3050.lastfm.ui.utils.DeviceUtils
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
        private val deviceUtils: DeviceUtils,
        private val cloudDataSource: CloudDataSource,
        private val diskDataSource: DiskDataSource,
        private val artistMapper: ArtistMapper,
        private val appExecutors: AppExecutors) : Repository {


    override fun insertTopArtist(listTopArtistModel: ListTopArtistModel): List<Long> {
        return diskDataSource.insertTopArtist(listTopArtistModel.artists.map { artistMapper.convert(it) })
    }

    override fun fetchTopArtist(): LiveData<ListTopArtistModel> {
        val responseMediatorLiveData = MediatorLiveData<ListTopArtistModel>()

        if (deviceUtils.isNetworkAvailable) {
            responseMediatorLiveData.addSource(cloudDataSource.getTopArtist("mexico"), { apiResponse ->
                if (apiResponse != null && apiResponse.isSuccessful && apiResponse.body != null) {
                    val models = apiResponse.body.topArtist.artist.map { artistMapper.convert(it) }

                    val listTopArtistModel = ListTopArtistModel()
                    listTopArtistModel.artists.addAll(models)
                    responseMediatorLiveData.postValue(listTopArtistModel)
                } else {
                    val errorResponse = ListTopArtistModel()
                    errorResponse.setError("Artist were not Found in this Country")
                    responseMediatorLiveData.postValue(errorResponse)
                }
            })
        } else {
            val errorResponse = ListTopArtistModel()
            errorResponse.setError("No internet Connection")
            responseMediatorLiveData.postValue(errorResponse)
        }

        return responseMediatorLiveData
    }

    override fun selectTopArtist(): LiveData<ListTopArtistModel> {
        return Transformations.map(diskDataSource.selectAllTopArtist(), { entities ->
            val model = ListTopArtistModel()
            model.artists.addAll(entities.map { entity -> artistMapper.convert(entity) })
            model
        })
    }

    /*override fun fetchArtist(artistName: String): LiveData<ArtistModel> {
        val mediatorResponse = MediatorLiveData<ArtistModel>()
        val entityLiveData = diskDataSource.selectArtist(artistName)

        mediatorResponse.addSource(entityLiveData, { artistEntity ->
            if (artistEntity != null) {
                if (deviceUtils.isNetworkAvailable) {
                    val artistLiveData = cloudDataSource.getArtistInfo(artistName)
                    mediatorResponse.addSource(artistLiveData, { response ->
                        if (response != null && response.isSuccessful && response.body != null) {
                            val model = artistMapper.convert(response.body.artist)
                            if (!model.error) {
                                appExecutors.diskIO.execute {
                                    diskDataSource.insertArtist(artistMapper.convertEntity(response.body.artist))
                                }
                            }
                            mediatorResponse.postValue(model)
                        } else {
                            val errorResponse = ArtistModel()
                            errorResponse.setError("Oops there was an error on our servers")
                            mediatorResponse.postValue(errorResponse)
                        }
                    })
                } else {
                    val errorResponse = ArtistModel()
                    errorResponse.setError("No Internet connection")
                    mediatorResponse.postValue(errorResponse)
                }
            } else {
                val errorResponse = ArtistModel()
                errorResponse.setError("$artistName was not found")
                mediatorResponse.postValue(errorResponse)
            }
        })
        return mediatorResponse
    }*/

    override fun fetchArtist(artistName: String): LiveData<ArtistModel> {
        val responseMediatorLiveData = MediatorLiveData<ArtistModel>()

        if(deviceUtils.isNetworkAvailable) {
            responseMediatorLiveData.addSource(cloudDataSource.getArtistInfo(artistName), { apiResponse ->
                if(apiResponse != null && apiResponse.isSuccessful && apiResponse.body != null) {
                    val model = apiResponse.body.artist
                    responseMediatorLiveData.postValue(artistMapper.convert(model))
                } else {
                    val errorResponse = ArtistModel()
                    errorResponse.setError("$artistName was not found")
                    responseMediatorLiveData.postValue(errorResponse)
                }
            })
        } else {
            val entityResponse = diskDataSource.selectArtist(artistName)
            if(entityResponse.value!!.artistId.isNullOrEmpty()) {
                val errorResponse = ArtistModel()
                errorResponse.setError("$artistName was not found")
                responseMediatorLiveData.postValue(errorResponse)
            } else {
                responseMediatorLiveData.addSource(entityResponse,{model ->
                    model
                })
            }
        }

        return responseMediatorLiveData
    }

    override fun insertArtist(artist: ArtistModel): Long {
        return diskDataSource.insertArtist(artistMapper.convert(artist))
    }

    override fun selectArtist(artistName: String): LiveData<ArtistModel> {
        return Transformations.map(diskDataSource.selectArtist(artistName), { entity ->
            if(entity == null){
                ArtistModel()
            } else {
                artistMapper.convert(entity)
            }

        })
    }
}
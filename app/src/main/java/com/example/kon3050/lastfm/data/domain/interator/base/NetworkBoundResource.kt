package com.example.kon3050.lastfm.data.domain.interator.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.example.kon3050.lastfm.AppExecutors
import com.example.kon3050.lastfm.data.domain.interator.result.Resource
import com.example.kon3050.lastfm.data.domain.model.base.BaseModel

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 *
 * You can read more about it in the [Architecture
 * Guide](https://developer.android.com/arch).
 * @param <ResultType>
 * @param <RequestType>
</RequestType></ResultType> */
abstract class NetworkBoundResource<DiskType : BaseModel, CloudType : BaseModel>
@MainThread
internal constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<DiskType>>()

    fun init(loading: DiskType) {
        result.value = Resource.loading(loading)
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            val should = shouldFetch(data)
            if (should) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { setValue(Resource.success(it!!)) }
            }
        }
    }


    @MainThread
    private fun setValue(value: Resource<DiskType>) {
        if (result.value != value) {
            result.value = value
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<DiskType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { setValue(Resource.loading(it!!)) }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            if (response != null && !response.error) {
                appExecutors.diskIO.execute {
                    saveCallResult(processResponse(response))
                    appExecutors.mainThread.execute {
                        result.addSource(loadFromDb()) { setValue(Resource.success(it!!)) }
                    }
                }
            } else {
                result.addSource(dbSource, { setValue(Resource.error(response!!.message, it!!)) })
            }
        }
    }

    protected fun onFetchFailed() {}

    fun asLiveData(): LiveData<Resource<DiskType>> = result

    @WorkerThread
    protected open fun processResponse(response: CloudType): CloudType = response

    @WorkerThread
    protected abstract fun saveCallResult(item: CloudType)

    @MainThread
    protected abstract fun shouldFetch(data: DiskType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<DiskType>

    @MainThread
    protected abstract fun createCall(): LiveData<CloudType>

}
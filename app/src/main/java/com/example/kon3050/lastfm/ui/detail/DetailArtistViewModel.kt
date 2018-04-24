package com.example.kon3050.lastfm.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.kon3050.lastfm.data.domain.Repository
import com.example.kon3050.lastfm.ui.mapper.UiMapper
import com.example.kon3050.lastfm.ui.model.ArtistUiModel
import javax.inject.Inject

class DetailArtistViewModel @Inject constructor(
        val repository: Repository,
        val uiMapper: UiMapper) : ViewModel() {

    private var artistName: MutableLiveData<String> = MutableLiveData()
    private var artist: LiveData<ArtistUiModel>

    init {
        artist = Transformations.switchMap(artistName, { name ->
            Transformations.map(repository.fetchArtist(name,""), { model ->
                uiMapper.convert(model)
            })
        })
    }



    fun getArtist():LiveData<ArtistUiModel> = artist

    fun setArtistName(artistsName: String) {
        if(artistsName.isNotEmpty()) {
            artistName.value = artistsName
        }
    }
}
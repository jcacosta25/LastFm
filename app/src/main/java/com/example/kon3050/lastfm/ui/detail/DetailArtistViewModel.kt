package com.example.kon3050.lastfm.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.kon3050.lastfm.data.domain.interator.UseCaseGetArtist
import com.example.kon3050.lastfm.ui.mapper.UiMapper
import com.example.kon3050.lastfm.ui.model.ArtistUiModel
import javax.inject.Inject

class DetailArtistViewModel @Inject constructor(
        private val useCaseGetArtist: UseCaseGetArtist,
        private val uiMapper: UiMapper) : ViewModel() {

    private var artistName: MutableLiveData<String> = MutableLiveData()
    private var artist: LiveData<ArtistUiModel>

    init {
        artist = Transformations.switchMap(artistName, { name ->
            Transformations.map(
                    useCaseGetArtist.execute(name),
                    { model -> uiMapper.convert(model.data) }
            )
        })
    }



    fun getArtist():LiveData<ArtistUiModel> = artist

    fun setArtistName(artistName: String) {
        if(artistName.isNotEmpty()) {
            this.artistName.value = artistName
        }
    }
}
package com.example.kon3050.lastfm.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.kon3050.lastfm.data.domain.interator.UseCaseGetTopArtist
import com.example.kon3050.lastfm.ui.mapper.UiMapper
import com.example.kon3050.lastfm.ui.model.ListTopArtistUiModel
import javax.inject.Inject

class TopArtistViewModel @Inject constructor(
        private val useCaseGetTopArtist: UseCaseGetTopArtist,
        private val uiMapper: UiMapper) : ViewModel() {

    private var topArtist: LiveData<ListTopArtistUiModel>

    init {
        topArtist = Transformations.map(useCaseGetTopArtist.asLiveData(), { response ->
            uiMapper.convert(response.data)
        })
    }

    fun loadTopArtist():LiveData<ListTopArtistUiModel> = topArtist
}
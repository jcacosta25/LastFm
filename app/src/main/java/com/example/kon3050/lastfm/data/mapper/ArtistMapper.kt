package com.example.kon3050.lastfm.data.mapper

import com.example.kon3050.lastfm.data.api.model.ArtistDetailResponse
import com.example.kon3050.lastfm.data.api.model.TopArtistResponse
import com.example.kon3050.lastfm.data.db.entity.ArtistEntity
import com.example.kon3050.lastfm.data.db.entity.TopArtistEntity
import com.example.kon3050.lastfm.data.domain.model.ArtistModel
import com.example.kon3050.lastfm.data.domain.model.TopArtistModel
import javax.inject.Inject

class ArtistMapper @Inject constructor() {

    fun convert(response: TopArtistResponse): TopArtistModel {
        return TopArtistModel(
                response.mbid,
                response.name,
                response.streamable,
                response.listeners,
                response.image[2].text

        )
    }

    fun convert(entity: TopArtistEntity): TopArtistModel {
        return TopArtistModel(
                entity.artistId,
                entity.artistName,
                entity.streamable,
                entity.artistListeners,
                entity.image
        )
    }

    fun convert(model: TopArtistModel): TopArtistEntity {
        return TopArtistEntity(
                model.artistId,
                model.artistName,
                model.artistListeners,
                model.streamable,
                model.image
        )
    }

    fun convertEntity(response: TopArtistResponse): TopArtistEntity {
        return TopArtistEntity(
                response.mbid,
                response.name,
                response.listeners,
                response.streamable,
                response.image[2].text
        )
    }

    fun convert(response: ArtistDetailResponse): ArtistModel {
        return ArtistModel(
                response.mbid,
                response.name,
                response.image[2].text,
                response.streamable,
                response.ontour,
                response.bio.published,
                response.bio.summary,
                response.bio.content
        )
    }

    fun convert(entity: ArtistEntity): ArtistModel {
        return ArtistModel(
                entity.artistId,
                entity.artistName,
                entity.image,
                entity.streamable,
                entity.onTour,
                entity.published,
                entity.summary,
                entity.content
        )
    }

    fun convert(model: ArtistModel): ArtistEntity {
        return ArtistEntity(
                model.artistId,
                model.artistName,
                model.streamable,
                model.image,
                model.onTour,
                model.published,
                model.summary,
                model.content
        )
    }

    fun convertEntity(response: ArtistDetailResponse): ArtistEntity {
        return ArtistEntity(
                response.mbid,
                response.name,
                response.streamable,
                response.image[2].text,
                response.ontour,
                response.bio.published,
                response.bio.summary,
                response.bio.content
        )
    }
}
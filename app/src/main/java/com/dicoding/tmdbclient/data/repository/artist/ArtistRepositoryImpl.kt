package com.dicoding.tmdbclient.data.repository.artist

import android.util.Log
import com.dicoding.tmdbclient.data.model.artist.Artist
import com.dicoding.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.dicoding.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.dicoding.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.dicoding.tmdbclient.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository {
    override suspend fun getArtists(): List<Artist> = getArtistsFromCache()

    override suspend fun updateArtists(): List<Artist> {
        val newListOfArtist = getArtistsFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistToDB(newListOfArtist)
        artistCacheDataSource.saveArtistToCache(newListOfArtist)
        return newListOfArtist
    }

    private suspend fun getArtistsFromAPI(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            val response = artistRemoteDataSource.getArtist()
            val body = response.body()
            if (body != null) artistList = body.artists
        } catch (e: Exception) {
            Log.i("MYTAG", e.message.toString())
        }
        return artistList
    }

    private suspend fun getArtistsFromDB(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            artistList = artistLocalDataSource.getArtistFromDB()
        } catch (e: Exception) {
            Log.i("MYTAG", e.message.toString())
        }
        if (artistList.isNotEmpty()) return artistList
        else {
            artistList = getArtistsFromAPI()
            artistLocalDataSource.saveArtistToDB(artistList)
        }
        return artistList
    }

    private suspend fun getArtistsFromCache(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            artistList = artistCacheDataSource.getArtistFromCache()
        } catch (e: Exception) {
            Log.i("MYTAG", e.message.toString())
        }
        if (artistList.isNotEmpty()) return artistList
        else {
            artistList = getArtistsFromDB()
            artistCacheDataSource.saveArtistToCache(artistList)
        }
        return artistList
    }
}
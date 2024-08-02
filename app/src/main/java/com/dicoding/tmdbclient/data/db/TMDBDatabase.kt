package com.dicoding.tmdbclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.tmdbclient.data.model.artist.Artist
import com.dicoding.tmdbclient.data.model.movie.Movie
import com.dicoding.tmdbclient.data.model.tvshow.TvShow

@Database(
    entities = [
        Movie::class,
        TvShow::class,
        Artist::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao
    abstract fun ArtistDao(): ArtistDao
    abstract fun TvShowDao(): TvShowDao
}
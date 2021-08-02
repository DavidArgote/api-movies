package com.davidargote.api_movies.repository.local

import androidx.room.*
import com.davidargote.api_movies.model.local.MovieEntity
import com.google.android.material.circularreveal.CircularRevealHelper

@Dao
interface MovieDao {

    @Query("Select * FROM movieentity")
    suspend fun getAllMovies() : List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

}
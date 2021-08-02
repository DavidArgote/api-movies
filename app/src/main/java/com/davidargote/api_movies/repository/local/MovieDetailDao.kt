package com.davidargote.api_movies.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davidargote.api_movies.model.local.MovieDetailEntity

@Dao
interface MovieDetailDao {

    @Query("SELECT * FROM MovieDetailEntity WHERE id = :id")
    suspend fun getMovie(id: Int) : MovieDetailEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieDetailEntity)

}
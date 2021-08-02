package com.davidargote.api_movies.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davidargote.api_movies.model.service.Movie
import com.davidargote.api_movies.model.service.MovieList
import com.davidargote.api_movies.model.service.toMovieEntity
import com.google.gson.annotations.SerializedName

@Entity
class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = -1,
    @ColumnInfo(name = "original_title")
    val original_title: String = "",
    @ColumnInfo(name = "original_language")
    val original_language: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = -1.0,
    @ColumnInfo(name = "poster_path")
    val poster_path: String = "",
    @ColumnInfo(name = "release_date")
    val release_date: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int = -1,
    @ColumnInfo(name = "like")
    val like: Boolean = false
)

fun List<MovieEntity>.toMovieList(): MovieList {
    val resultList = mutableListOf<Movie>()
    this.forEach { movieEntity ->
        resultList.add(movieEntity.toMovie())
    }
    return MovieList(resultList)
}

fun MovieEntity.toMovie(): Movie = Movie(
    id = this.id,
    original_title = this.original_title,
    original_language = this.original_language,
    overview = this.overview,
    popularity = this.popularity,
    poster_path = this.poster_path,
    release_date = this.release_date,
    title = this.title,
    video = this.video,
    vote_average = this.vote_average,
    vote_count = this.vote_count
)
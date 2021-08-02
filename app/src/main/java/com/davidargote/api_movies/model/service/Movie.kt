package com.davidargote.api_movies.model.service

import android.os.Parcelable
import com.davidargote.api_movies.model.local.MovieEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int = -1,
    val original_title: String = "",
    val original_language: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = -1
) : Parcelable

data class MovieList(val results: List<Movie> = listOf())

fun Movie.toMovieEntity(): MovieEntity = MovieEntity(
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
    vote_count = this.vote_count,
    like = false
)
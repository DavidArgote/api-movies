package com.davidargote.api_movies.model.service

import android.os.Parcelable
import com.davidargote.api_movies.model.local.MovieDetailEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetail(
    val id: Int,
    val backdrop_path: String = "",
    val status: String = "",
    val original_title: String = "",
    val release_date: String = "",
    val runtime: Int = -1,
    val overview: String = "",
    val poster_path: String = "",
    val vote_average: Double = -1.0,
    val vote_count: Double = -1.0,
    val original_language: String = ""
) : Parcelable

fun MovieDetail.toMovieDetailEntity() = MovieDetailEntity(
    this.id,
    this.backdrop_path,
    this.status,
    this.original_title,
    this.release_date,
    this.runtime,
    this.overview,
    this.poster_path,
    this.vote_average,
    this.vote_count,
    this.original_language
)
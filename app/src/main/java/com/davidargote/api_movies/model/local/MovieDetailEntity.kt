package com.davidargote.api_movies.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davidargote.api_movies.model.service.MovieDetail

@Entity
data class MovieDetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String = "",
    @ColumnInfo(name = "status")
    val status: String = "",
    @ColumnInfo(name = "original_title")
    val original_title: String = "",
    @ColumnInfo(name = "release_date")
    val release_date: String = "",
    @ColumnInfo(name = "runtime")
    val runtime: Int = -1,
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "poster_path")
    val poster_path: String = "",
    @ColumnInfo(name = "vote_average")
    val vote_average: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val vote_count: Double = -1.0,
    @ColumnInfo(name = "original_language")
    val original_language: String = ""
)

fun MovieDetailEntity.toMovieDetail() : MovieDetail = MovieDetail(
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

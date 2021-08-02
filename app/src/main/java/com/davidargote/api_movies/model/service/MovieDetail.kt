package com.davidargote.api_movies.model.service

import android.os.Parcelable
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
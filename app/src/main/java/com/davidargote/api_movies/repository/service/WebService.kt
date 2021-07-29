package com.davidargote.api_movies.repository.service

import com.davidargote.api_movies.application.Constants
import com.davidargote.api_movies.model.remote.Movie
import com.davidargote.api_movies.model.remote.MovieList
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class WebService constructor(private val client: HttpClient) {

    suspend fun getMovies(): MovieList {
        return client.get {
            method = HttpMethod.Get
            url("${Constants.BASE_URL}movie/popular?api_key=${Constants.TOKEN}")
        }
    }

}
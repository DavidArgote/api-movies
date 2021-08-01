package com.davidargote.api_movies.repository.service

import com.davidargote.api_movies.application.Constants
import com.davidargote.api_movies.model.remote.Movie
import com.davidargote.api_movies.model.remote.MovieDetail
import com.davidargote.api_movies.model.remote.MovieList
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class WebService @Inject constructor() {

    private val client: HttpClient = HttpClient(CIO) {
        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    suspend fun getMovies(): MovieList {
        return client.get {
            method = HttpMethod.Get
            url("${Constants.BASE_URL}movie/popular?api_key=${Constants.TOKEN}")
        }
    }

    suspend fun getMovieById(id: Int): MovieDetail {
        return client.get {
            method = HttpMethod.Get
            url("${Constants.BASE_URL}movie/$id?api_key=${Constants.TOKEN}")
        }
    }

}
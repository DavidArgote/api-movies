package com.davidargote.api_movies.repository

import com.davidargote.api_movies.model.remote.MovieList
import com.davidargote.api_movies.repository.service.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepository constructor(private val service: WebService) {

    fun getMovies(): Flow<MovieList> = flow {
        emit(service.getMovies())
    }.flowOn(Dispatchers.IO)

}
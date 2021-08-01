package com.davidargote.api_movies.repository

import com.davidargote.api_movies.model.remote.MovieDetail
import com.davidargote.api_movies.repository.service.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(private val service: WebService) {

    suspend fun getMovieById(id: Int): Flow<MovieDetail> = flow {
        emit(service.getMovieById(id))
    }.flowOn(Dispatchers.IO)

}
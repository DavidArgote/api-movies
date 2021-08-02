package com.davidargote.api_movies.repository

import com.davidargote.api_movies.application.InternetCheck
import com.davidargote.api_movies.model.local.toMovieDetail
import com.davidargote.api_movies.model.service.MovieDetail
import com.davidargote.api_movies.model.service.toMovieDetailEntity
import com.davidargote.api_movies.repository.local.MovieDetailDao
import com.davidargote.api_movies.repository.service.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val service: WebService,
    private val movieDetailDao: MovieDetailDao
) {

    suspend fun getMovieById(id: Int): Flow<MovieDetail> = flow {
        if (InternetCheck.isNetworkAvailable()) {
            val movie = service.getMovieById(id).toMovieDetailEntity()
            movieDetailDao.insertMovie(movie)
            emit(movieDetailDao.getMovie(id).toMovieDetail())
        } else {
            emit(movieDetailDao.getMovie(id).toMovieDetail())
        }
    }.flowOn(Dispatchers.IO)

}
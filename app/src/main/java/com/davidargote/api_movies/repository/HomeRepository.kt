package com.davidargote.api_movies.repository

import com.davidargote.api_movies.application.InternetCheck
import com.davidargote.api_movies.model.local.toMovieList
import com.davidargote.api_movies.model.service.MovieList
import com.davidargote.api_movies.model.service.toMovieEntity
import com.davidargote.api_movies.repository.local.MovieDao
import com.davidargote.api_movies.repository.service.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(private val service: WebService, private val movieDao: MovieDao) {

    fun getMovies(): Flow<MovieList> = flow {
        if (InternetCheck.isNetworkAvailable()) {
            val data = service.getMovies()
            data.results.forEach { movie ->
                movieDao.insertMovie(movie.toMovieEntity())
            }
            emit(movieDao.getAllMovies().toMovieList())
        } else {
            emit(movieDao.getAllMovies().toMovieList())
        }
    }.flowOn(Dispatchers.IO)

}
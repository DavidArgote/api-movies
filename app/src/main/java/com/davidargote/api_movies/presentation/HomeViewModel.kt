package com.davidargote.api_movies.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidargote.api_movies.application.ServiceState
import com.davidargote.api_movies.model.service.MovieList
import com.davidargote.api_movies.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _serviceStateFlow: MutableStateFlow<ServiceState<MovieList>> =
        MutableStateFlow(ServiceState.Empty)

    val serviceStateFlow: StateFlow<ServiceState<MovieList>> = _serviceStateFlow

    fun getMovies() = viewModelScope.launch {
        repository.getMovies()
            .onStart {
                _serviceStateFlow.value = ServiceState.Loading
            }.catch { exception ->
                Log.i("ERROR", exception.message, exception)
                _serviceStateFlow.value = ServiceState.Failure(exception)
            }.collect { response ->
                _serviceStateFlow.value = ServiceState.Success(response)
            }
    }

}
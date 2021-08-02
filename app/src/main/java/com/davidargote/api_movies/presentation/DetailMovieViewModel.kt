package com.davidargote.api_movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidargote.api_movies.application.ServiceState
import com.davidargote.api_movies.model.service.MovieDetail
import com.davidargote.api_movies.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {

    private val _serviceStateFlow: MutableStateFlow<ServiceState<MovieDetail>> =
        MutableStateFlow(ServiceState.Empty)

    val serviceStateFlow: StateFlow<ServiceState<MovieDetail>> = _serviceStateFlow

    fun getMovie(id: Int) = viewModelScope.launch {
        repository.getMovieById(id)
            .onStart {
                _serviceStateFlow.value = ServiceState.Loading
            }.catch { exception ->
                _serviceStateFlow.value = ServiceState.Failure(exception)
            }.collect { response ->
                _serviceStateFlow.value = ServiceState.Success(response)
            }
    }

}

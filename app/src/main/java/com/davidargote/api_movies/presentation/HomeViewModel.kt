package com.davidargote.api_movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.davidargote.api_movies.application.ServiceState
import com.davidargote.api_movies.model.remote.MovieList
import com.davidargote.api_movies.repository.HomeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val repository: HomeRepository) : ViewModel() {

    private val _serviceStateFlow: MutableStateFlow<ServiceState<MovieList>> =
        MutableStateFlow(ServiceState.Empty)

    val serviceStateFlow: StateFlow<ServiceState<MovieList>> = _serviceStateFlow

    fun getMovies() = viewModelScope.launch {
        repository.getMovies()
            .onStart {
                _serviceStateFlow.value = ServiceState.Loading
            }.catch { exception ->
                _serviceStateFlow.value = ServiceState.Failure(exception)
            }.collect { response ->
                _serviceStateFlow.value = ServiceState.Success(response)
            }
    }

}

class HomeViewModelFactory(private val repository: HomeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeRepository::class.java).newInstance(repository)
    }
}
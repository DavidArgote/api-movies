package com.davidargote.api_movies.application

sealed class ServiceState<out T> {
    object Empty : ServiceState<Nothing>()
    class Failure(val error: Throwable) : ServiceState<Nothing>()
    class Success<out T>(val data: T) : ServiceState<T>()
    object Loading: ServiceState<Nothing>()
}

package com.davidargote.api_movies.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.davidargote.api_movies.R
import com.davidargote.api_movies.application.ServiceState
import com.davidargote.api_movies.presentation.HomeViewModel
import com.davidargote.api_movies.presentation.HomeViewModelFactory
import com.davidargote.api_movies.repository.HomeRepository
import com.davidargote.api_movies.repository.service.ClientService
import com.davidargote.api_movies.repository.service.WebService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(HomeRepository(WebService(ClientService.getClientInstance())))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies()
        lifecycleScope.launchWhenStarted {
            viewModel.serviceStateFlow.collect {
                when(it) {
                    is ServiceState.Success -> {
                        Log.i(TAG, it.data.toString())
                    }
                    is ServiceState.Failure -> {
                        Log.e(TAG, it.error.toString())
                    }
                    is ServiceState.Loading -> {
                        Log.i(TAG, "Loading...")
                    }
                    is ServiceState.Empty -> {
                        Snackbar.make(view, "Datos no encontrados!", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    companion object {
        val TAG = "HomeFragment"
    }

}
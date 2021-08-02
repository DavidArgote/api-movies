package com.davidargote.api_movies.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidargote.api_movies.R
import com.davidargote.api_movies.application.ServiceState
import com.davidargote.api_movies.databinding.FragmentHomeBinding
import com.davidargote.api_movies.model.service.Movie
import com.davidargote.api_movies.model.service.MovieList
import com.davidargote.api_movies.presentation.HomeViewModel
import com.davidargote.api_movies.ui.home.adapter.ListMoviesAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ListMoviesAdapter.OnMovieClickListener {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies()

        lifecycleScope.launchWhenStarted {
            viewModel.serviceStateFlow.collect {
                when (it) {
                    is ServiceState.Success -> {
                        binding.progressList.visibility = View.GONE
                        binding.rcListMovies.visibility = View.VISIBLE
                        bindingRecycler(it.data)
                    }
                    is ServiceState.Failure -> {
                        binding.progressList.visibility = View.GONE
                        Snackbar.make(
                            view,
                            "Error al intentar traer los datos",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    is ServiceState.Loading -> {
                        binding.progressList.visibility = View.VISIBLE
                    }
                    is ServiceState.Empty -> {
                        binding.progressList.visibility = View.GONE
                        Snackbar.make(view, "Datos no encontrados", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    private fun bindingRecycler(data: MovieList) {
        with(binding.rcListMovies) {
            layoutManager = when {
                COLUMN_COUNT <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, COLUMN_COUNT)
            }
            adapter = ListMoviesAdapter(data.results, this@HomeFragment, viewModel)
            binding.rcListMovies.adapter = adapter
        }
    }

    override fun onMovieClick(movie: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment2(movieId = movie.id)
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "HomeFragment"
        private const val COLUMN_COUNT = 2
    }

}
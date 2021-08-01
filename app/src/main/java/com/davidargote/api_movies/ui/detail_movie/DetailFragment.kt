package com.davidargote.api_movies.ui.detail_movie


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.davidargote.api_movies.application.ServiceState
import com.davidargote.api_movies.databinding.FragmentDetailBinding
import com.davidargote.api_movies.model.remote.MovieDetail
import com.davidargote.api_movies.presentation.DetailMovieViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailMovieViewModel by viewModels()

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovie(args.movieId)

        lifecycleScope.launchWhenCreated {
            viewModel.serviceStateFlow.collect {
                when (it) {
                    is ServiceState.Success -> {
                        binding.pbMovieDetail.visibility = View.GONE
                        bindingMovie(it.data)
                    }
                    is ServiceState.Failure -> {
                        Snackbar.make(
                            view,
                            "Error al cargar la información de la pelicula",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    is ServiceState.Loading -> {
                        binding.pbMovieDetail.visibility = View.VISIBLE
                    }
                    else -> {
                        Snackbar.make(
                            view,
                            "Error!",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

    }

    private fun bindingMovie(movie: MovieDetail) {
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            .centerCrop()
            .into(binding.imgMiniDetail)
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/${movie.backdrop_path}")
            .centerCrop()
            .into(binding.imgPosterDetail)

        binding.tvTitleDetail.text = movie.original_title
        binding.tvDescriptionDetail.text = movie.overview

        binding.tvCalificationDetail.text = movie.vote_average.toString()
        binding.tvDateDetail.text = movie.release_date

        binding.tvLanguageDetail.text = movie.original_language

    }

    companion object {
        const val TAG = "DetailFragment"
    }

}
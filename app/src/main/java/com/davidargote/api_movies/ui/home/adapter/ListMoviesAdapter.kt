package com.davidargote.api_movies.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davidargote.api_movies.R
import com.davidargote.api_movies.databinding.ItemListBinding
import com.davidargote.api_movies.model.service.Movie
import com.davidargote.api_movies.application.BaseViewHolder
import com.davidargote.api_movies.presentation.HomeViewModel

class ListMoviesAdapter constructor(
    private val moviesList: List<Movie>,
    private val itemClickListener: OnMovieClickListener,
    private val viewModel: HomeViewModel
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ListMoviesViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onMovieClick(moviesList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ListMoviesViewHolder -> holder.bind(moviesList[position])
        }
    }

    override fun getItemCount(): Int = moviesList.size

    private inner class ListMoviesViewHolder(val binding: ItemListBinding, val context: Context) :
        BaseViewHolder<Movie>(binding.root) {

        override fun bind(item: Movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}")
                .centerCrop().into(binding.itemMovieImage)

            binding.textItemDateRelease.text = item.release_date
            binding.btnFavItem.setImageResource(getIdIconLike(item.like))

            binding.btnFavItem.setOnClickListener {
                item.like = !item.like
                binding.btnFavItem.setImageResource(getIdIconLike(item.like))
                viewModel.updateMovie(item)
            }

        }

        private fun getIdIconLike(value: Boolean) : Int {
            return if (value) {
                R.drawable.ic_baseline_favorite_24
            } else {
                R.drawable.ic_baseline_favorite_border_24
            }
        }

    }

}
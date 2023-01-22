package com.mark.moviesexpert.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mark.moviesexpert.BR
import com.mark.moviesexpert.data.models.Movie
import com.mark.moviesexpert.databinding.ViewHolderMovieBinding

class MoviePagingAdapter :
    PagingDataAdapter<com.mark.moviesexpert.data.models.Movie, MoviePagingAdapter.MyViewHolder>(
        DIFF_UTIL
    ) {
    var onCLick: ((String) -> Unit)? = null

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<com.mark.moviesexpert.data.models.Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: com.mark.moviesexpert.data.models.Movie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: com.mark.moviesexpert.data.models.Movie,
                newItem: com.mark.moviesexpert.data.models.Movie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun onMovieClick(listener: (String) -> Unit) {
        onCLick = listener
    }

    inner class MyViewHolder(val viewDataBinding: com.mark.moviesexpert.databinding.ViewHolderMovieBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.movie, getItem(position))
        holder.viewDataBinding.root.setOnClickListener {
            onCLick?.let {
                it(getItem(position)?.id!!.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}
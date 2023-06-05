package com.mdq.mdayangcomovies.ui.omdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mdq.mdayangcomovies.data.model.OMDBModel
import com.mdq.mdayangcomovies.databinding.LayoutOmdbItemBinding

class OMDBPagingAdapter constructor(val onItemClick: (omdb: OMDBModel) -> Unit) :
    PagingDataAdapter<OMDBModel, OMDBPagingAdapter.MovieViewHolder>(
        diffCallback
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutOmdbItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }

    inner class MovieViewHolder(val bind: LayoutOmdbItemBinding) :
        RecyclerView.ViewHolder(bind.root) {

        fun bindView(omdb: OMDBModel) {
            bind.txtTitle.text = omdb.title
            bind.txtYear.text = omdb.year

            Glide.with(bind.root)
                .load(omdb.poster)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(bind.imgPoster)

            bind.root.setOnClickListener {
                onItemClick(omdb)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<OMDBModel>() {
            override fun areItemsTheSame(
                oldItem: OMDBModel,
                newItem: OMDBModel
            ) = oldItem.imdbID == newItem.imdbID

            override fun areContentsTheSame(
                oldItem: OMDBModel,
                newItem: OMDBModel
            ) = oldItem == newItem

        }
    }
}
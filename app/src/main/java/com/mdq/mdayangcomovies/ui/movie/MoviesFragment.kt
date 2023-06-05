package com.mdq.mdayangcomovies.ui.movie

import android.os.Bundle
import android.view.View
import com.mdq.mdayangcomovies.ui.omdb.OMDBFragment

class MoviesFragment() : OMDBFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.omdbMovies.observe(viewLifecycleOwner) {
            pagingAdapter?.submitData(viewLifecycleOwner.lifecycle,it)
            viewModel.stopLoading()
        }

    }
}
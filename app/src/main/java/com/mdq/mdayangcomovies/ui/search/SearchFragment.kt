package com.mdq.mdayangcomovies.ui.search

import android.os.Bundle
import android.view.View
import com.mdq.mdayangcomovies.ui.omdb.OMDBFragment

class SearchFragment() : OMDBFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.omdbSearch.observe(viewLifecycleOwner) {
            pagingAdapter?.submitData(viewLifecycleOwner.lifecycle,it)
            viewModel.stopLoading()
        }
    }
}
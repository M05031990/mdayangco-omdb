package com.mdq.mdayangcomovies.ui.episodes

import android.os.Bundle
import android.view.View
import com.mdq.mdayangcomovies.ui.omdb.OMDBFragment

class EpisodesFragment : OMDBFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.omdbEpisodes.observe(viewLifecycleOwner) {
            pagingAdapter?.submitData(viewLifecycleOwner.lifecycle,it)
            viewModel.stopLoading()
        }
    }
}
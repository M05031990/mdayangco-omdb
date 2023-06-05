package com.mdq.mdayangcomovies.ui.series

import android.os.Bundle
import android.view.View
import com.mdq.mdayangcomovies.ui.omdb.OMDBFragment

class SeriesFragment : OMDBFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.omdbSeries.observe(viewLifecycleOwner) {
            pagingAdapter?.submitData(viewLifecycleOwner.lifecycle,it)
            viewModel.stopLoading()
        }
    }
}
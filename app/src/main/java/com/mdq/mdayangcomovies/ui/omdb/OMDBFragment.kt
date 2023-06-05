package com.mdq.mdayangcomovies.ui.omdb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mdq.mdayangcomovies.databinding.FragmentOmdbBinding
import com.mdq.mdayangcomovies.ui.details.DetailsActivity

open class OMDBFragment : Fragment() {

    lateinit var binding: FragmentOmdbBinding
    val viewModel: OMDBViewModel by activityViewModels()

    var pagingAdapter: OMDBPagingAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOmdbBinding.inflate(inflater)
        initialize()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refresh()

        viewModel.loading.observe(viewLifecycleOwner) {
            progressLoading(it)
        }
    }

    private fun initialize() {
        pagingAdapter = OMDBPagingAdapter {
            DetailsActivity.startActivity(requireActivity(), it.imdbID)
        }
        binding.recyclerViewOMDB.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = pagingAdapter
        }
    }

    fun progressLoading(loading: Boolean) {
        binding.progressIndicator.visibility = if (loading) View.VISIBLE else View.GONE
    }
}
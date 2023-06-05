package com.mdq.mdayangcomovies.ui.omdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mdq.mdayangcomovies.data.api.OMDBType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OMDBViewModel @Inject constructor(private val omdbPagingSource: OMDBPagingSource) :
    ViewModel() {

    private val _refresh: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean>
        get() {
            return _refresh
        }

    private val _search: MutableLiveData<String?> = MutableLiveData()

    var omdbMovies = _refresh.switchMap {
        if (it) getPager(OMDBType.movie)
        else null

    }

    var omdbSeries = _refresh.switchMap {
        if (it) getPager(OMDBType.series)
        else null

    }

    var omdbEpisodes = _refresh.switchMap {
        if (it) getPager(OMDBType.episode)
        else null

    }

    var omdbSearch = _search.switchMap { getPager(search = it) }

    fun refresh() {
        _refresh.value = true
    }

    fun getSearch(search: String?) {
        _search.value = search
    }

    fun isLoading() = loading.value?:false

    fun stopLoading(){
        _refresh.value = false
    }

    private fun getPager(type: OMDBType? = null, search: String? = null) = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 100,
            initialLoadSize = 10
        ),
        pagingSourceFactory = {
            omdbPagingSource(type, search)
        }
    ).liveData
}
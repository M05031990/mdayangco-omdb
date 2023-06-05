package com.mdq.mdayangcomovies.data.api

import com.mdq.mdayangcomovies.BuildConfig
import com.mdq.mdayangcomovies.data.model.OMDBModel
import com.mdq.mdayangcomovies.data.model.OMDBSearch
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface OMDBAPiService {

    companion object {
        private const val QUERY_SEARCH = "s"
        private const val QUERY_ID = "i"
        private const val QUERY_TYPE = "type"
        private const val QUERY_PLOT = "plot"
        private const val QUERY_PAGE = "page"
        private const val QUERY_YEAR = "y"

        private const val DEFAULT_PAGE = 1

        private const val  APPEND_KEY = "?apikey="+BuildConfig.API_KEY
    }

    @GET(APPEND_KEY)
    suspend fun getOMDB(
        @Query(QUERY_SEARCH) search: String? = null,
        @Query(QUERY_ID) imdbID: String? = null,
        @Query(QUERY_TYPE) type: String? = null,
        @Query(QUERY_PLOT) plot: String? = OMDBPlot.short.name,
        @Query(QUERY_PAGE) page: Int? = DEFAULT_PAGE,
        @Query(QUERY_YEAR) year: Int? = null,
    ): OMDBSearch

    @GET(APPEND_KEY)
    suspend fun getOMDBDetail(
        @Query(QUERY_ID) imdbID: String,
        @Query(QUERY_PLOT) plot: String? = OMDBPlot.full.name,
    ): OMDBModel


}
package com.mdq.mdayangcomovies.domain

import com.mdq.mdayangcomovies.data.api.OMDBType
import com.mdq.mdayangcomovies.data.datasource.OMDBDataSource
import com.mdq.mdayangcomovies.data.model.DataResponse
import com.mdq.mdayangcomovies.data.model.OMDBModel
import com.mdq.mdayangcomovies.ui.omdb.OMDBPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDetailUseCase @Inject constructor(private val omdbDataSource: OMDBDataSource) {
    suspend operator fun invoke(imdbID: String): DataResponse<OMDBModel> {
        return omdbDataSource.getDetail(imdbID)
    }
}
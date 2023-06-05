package com.mdq.mdayangcomovies.ui.omdb

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mdq.mdayangcomovies.data.api.OMDBType
import com.mdq.mdayangcomovies.data.datasource.OMDBDataSource
import com.mdq.mdayangcomovies.data.model.DataResponse
import com.mdq.mdayangcomovies.data.model.OMDBModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OMDBPagingSource @Inject constructor(private val omdbDataSource: OMDBDataSource) :
    PagingSource<Int, OMDBModel>() {
    var selectType: OMDBType? = null
    var search: String? = null
    operator fun invoke(type: OMDBType?= null, search: String? = null): OMDBPagingSource {
        selectType = type
        this.search = if (search?.trim()?.isEmpty() == true) null else search
        return this
    }

    override fun getRefreshKey(state: PagingState<Int, OMDBModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OMDBModel> {
        val startIndex = 1
        val atIndex = params.key ?: startIndex

        return try {
            val response = search?.let {
                omdbDataSource.getSearch(selectType, it, atIndex)
            }?:omdbDataSource.getLatest(selectType, atIndex)
            when (response) {
                is DataResponse.Success -> {
                    LoadResult.Page(
                        data = response.data,
                        prevKey = if (atIndex == startIndex) null else atIndex - 1,
                        nextKey = if (response.data.isEmpty()) null else atIndex + 1
                    )
                }
                is DataResponse.Error -> {
                    LoadResult.Error(response.throwable)
                }
            }

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
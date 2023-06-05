package com.mdq.mdayangcomovies.data.datasource

import com.mdq.mdayangcomovies.data.api.OMDBAPiService
import com.mdq.mdayangcomovies.data.api.OMDBPlot
import com.mdq.mdayangcomovies.data.api.OMDBType
import com.mdq.mdayangcomovies.data.model.DataResponse
import com.mdq.mdayangcomovies.data.model.OMDBModel
import com.mdq.mdayangcomovies.data.room.AppDatabase
import com.mdq.mdayangcomovies.utils.AppUtility
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OMDBDataSource @Inject constructor(
    private val omdbAPiService: OMDBAPiService,
    private val appDatabase: AppDatabase,
    private val appUtility: AppUtility
) {
    suspend fun getLatest(type: OMDBType? = null, page: Int): DataResponse<List<OMDBModel>> {
        return loadData(onlineTask = {
            val tasks = getListFromRemote(
                type = type,
                search = "2023",
                year = 2023,
                page = page
            ).map {
                it.page = page
                it
            }
            saveAllToLocal(tasks)
            tasks
        }, offlineTask = {
            getListFromLocal(type, page)
        })
    }

    suspend fun getSearch(type: OMDBType? = null, search: String, page: Int): DataResponse<List<OMDBModel>> {
        return loadData(onlineTask = {
            val tasks = getListFromRemote(
                type = type,
                search = search,
                page = page
            ).map {
                it.page = page
                it
            }
            saveAllToLocal(tasks)
            tasks
        }, offlineTask = {
            getListFromLocal(type, page)
        })
    }

    suspend fun getDetail(imdbID: String): DataResponse<OMDBModel> {
        return loadData(onlineTask = {
            getDetailFromRemote(imdbID)
        }, offlineTask = {
            getDetailFromLocal(imdbID)
        })
    }

    private suspend fun getListFromRemote(type: OMDBType? = null, search: String, year: Int? = null, page: Int): List<OMDBModel> {
        return omdbAPiService.getOMDB(
            type = type?.name,
            search = search,
            year = year,
            page = page
        ).data ?: emptyList()
    }

    private suspend fun getListFromLocal(type: OMDBType? = null, page: Int): List<OMDBModel> =
        appDatabase.omdbDao().getAll(
            type = type?.name?:"",
            page = page
        ) ?: emptyList()

    private suspend fun saveAllToLocal(list: List<OMDBModel>) {
        appDatabase.omdbDao().save(list)
    }

    private suspend fun getDetailFromRemote(imdbID: String): OMDBModel {
        return omdbAPiService.getOMDBDetail(imdbID)
    }

    private suspend fun getDetailFromLocal(imdbID: String): OMDBModel {
        return appDatabase.omdbDao().get(imdbID)
    }

    private suspend fun <T : Any> loadData(
        onlineTask: suspend () -> T,
        offlineTask: suspend () -> T
    ): DataResponse<T> {
        return try {
            if (appUtility.hasNetworkConnection()) {
                DataResponse.Success(onlineTask())
            } else {
                DataResponse.Success(offlineTask())
            }
        } catch (t: Throwable) {
            DataResponse.Error(t)
        }
    }

}
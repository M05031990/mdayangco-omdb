package com.mdq.mdayangcomovies.data.room.dao

import androidx.room.*
import com.mdq.mdayangcomovies.data.model.OMDBModel
/**
 * Created by Michelle Dayangco on 3/21/21.
 */
@Dao
interface OMDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(list: List<OMDBModel>)

    @Query("SELECT * FROM OMDBModel")
    suspend fun getAll() : List<OMDBModel>?

    @Query("SELECT * FROM OMDBModel WHERE type = :type and page = :page")
    suspend fun getAll(type: String, page: Int) : List<OMDBModel>?

    @Query("SELECT * FROM OMDBModel WHERE imdbID = :id")
    fun get(id: String) : OMDBModel

    @Query("DELETE FROM OMDBModel")
    suspend fun deleteAll()
}
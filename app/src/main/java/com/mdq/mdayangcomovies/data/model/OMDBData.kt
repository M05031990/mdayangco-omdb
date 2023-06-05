package com.mdq.mdayangcomovies.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class OMDBModel(
    @PrimaryKey
    @SerializedName("imdbID") val imdbID: String,

    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Rated") val rated: String? = null,
    @SerializedName("Released") val released: String? = null,
    @SerializedName("Runtime") val runtime: String? = null,
    @SerializedName("Genre") val genre: String? = null,
    @SerializedName("Director") val director: String? = null,
    @SerializedName("Writer") val writer: String? = null,
    @SerializedName("Actors") val actors: String? = null,
    @SerializedName("Plot") val plot: String? = null,
    @SerializedName("Language") val language: String? = null,
    @SerializedName("Country") val country: String? = null,
    @SerializedName("Awards") val awards: String? = null,
    @SerializedName("Ratings") val ratings: List<Rating>? = emptyList(),
    @SerializedName("Metascore") val metascore: String? = null,
    @SerializedName("imdbRating") val imdbRating: String? = null,
    @SerializedName("imdbVotes") val imdbVotes: String? = null,
    @SerializedName("DVD") val dvd: String? = null,
    @SerializedName("BoxOffice") val boxOffice: String? = null,
    @SerializedName("Production") val production: String? = null,
    @SerializedName("Website") val website: String? = null,
    @SerializedName("Response") val response: String? = null,
    @SerializedName("PageIndex") var page: Int? = 1,
): Parcelable

@Parcelize
data class Rating(
    @SerializedName("Source") val source: String? = null,
    @SerializedName("Value") val value: String? = null,
): Parcelable
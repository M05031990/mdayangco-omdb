package com.mdq.mdayangcomovies.data.model

import com.google.gson.annotations.SerializedName

data class OMDBSearch(
    @SerializedName("Search") val data: List<OMDBModel>? = emptyList(),
    @SerializedName("Response") val success: String? = null,
    @SerializedName("Error") val error: String? = null
)
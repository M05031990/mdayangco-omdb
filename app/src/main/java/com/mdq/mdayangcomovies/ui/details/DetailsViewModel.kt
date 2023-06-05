package com.mdq.mdayangcomovies.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdq.mdayangcomovies.data.model.DataResponse
import com.mdq.mdayangcomovies.data.model.OMDBModel
import com.mdq.mdayangcomovies.domain.GetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val getDetailUseCase: GetDetailUseCase) :
    ViewModel() {

    fun getDetails(imdbID: String, response: (DataResponse<OMDBModel>) -> Unit) {
        viewModelScope.launch {
            val result = async { getDetailUseCase(imdbID) }
            response(result.await())
        }
    }
}
package com.example.radiofranceapp.presentation.show_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.Optional
import com.example.radiofranceapp.common.Constants.BRAND_ID_ARGUMENT
import com.example.radiofranceapp.common.Constants.ITEMS_LIMIT
import com.example.radiofranceapp.common.Constants.UNKNOWN_STATION
import com.example.radiofranceapp.common.Resource
import com.example.radiofranceapp.domain.usecases.GetShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(
    private val getShowsUseCase: GetShowsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {


    private val _state = MutableStateFlow(ShowListState())
    val state = _state.asStateFlow()
    val station = savedStateHandle.get<String>(BRAND_ID_ARGUMENT) ?: UNKNOWN_STATION

    init {
        getShows(Optional.present(ITEMS_LIMIT), Optional.absent())
    }


    fun getShows(limit: Optional<Int?>, after: Optional<String?>){
        getShowsUseCase(station, limit, after).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ShowListState(shows = result.data)
                }
                is Resource.Error -> {
                    _state.value = ShowListState(error =  result.message ?: "An unexepted error occured")
                }
                is Resource.Loading -> {
                    _state.value = ShowListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
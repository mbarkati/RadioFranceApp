package com.example.radiofranceapp.presentation.brand_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radiofranceapp.common.Resource
import com.example.radiofranceapp.domain.usecases.GetBrandsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
    private val getBrandsUseCase: GetBrandsUseCase
): ViewModel() {

    private val _state = mutableStateOf(BrandListState())
    val state: State<BrandListState> = _state

    init {
        getBrands()
    }

    private fun getBrands(){
        getBrandsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = BrandListState(brands = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = BrandListState(error =  result.message ?: "An unexepted error occured")
                }
                is Resource.Loading -> {
                    _state.value = BrandListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}
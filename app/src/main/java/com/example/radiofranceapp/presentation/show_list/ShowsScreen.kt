package com.example.radiofranceapp.presentation.show_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.apollographql.apollo3.api.Optional
import com.example.radiofranceapp.common.Constants
import com.example.radiofranceapp.domain.model.Shows
import com.example.radiofranceapp.presentation.Screen
import com.example.radiofranceapp.presentation.brand_list.components.BrandListItem


@Composable
fun ShowsScreen(
    viewModel: ShowsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        if(state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            state.shows?.let {
                ShowsList(
                    state = state,
                    shows = it,
                )
            }

        }
        }
    }

@Composable
fun ShowsList(
    state: ShowListState,
    shows: Shows,
) {

    Box(modifier = Modifier.fillMaxSize()) {
        if(state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            if(state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(shows.edges) { showEdge ->
                        ShowItem(show = showEdge.node)
                    }
                }
            }
        }
    }

}



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


@Composable
fun ShowsScreen(
    viewModel: ShowsViewModel = hiltViewModel(),
    onPaginate: (Optional<Int?>, Optional<String?>) -> Unit,
    ) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        if(state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            val infiniteListState = rememberLazyListState()
            state.shows?.let {
                ShowsList(
                    state = state,
                    infiniteListState = infiniteListState,
                    shows = it,
                    onPaginate = onPaginate
                )
            }

        }
        }
    }

@Composable
fun ShowsList(
    state: ShowListState,
    infiniteListState: LazyListState,
    shows: Shows,
    onPaginate: (Optional<Int?>, Optional<String?>) -> Unit
) {
    LazyColumn(
        state = infiniteListState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(shows.edges) { showEdge ->
            ShowItem(show = showEdge.node)
        }
        if (state.isLoading) {
            item {
                ProgressIndicator()
            }
        }
    }
    if (infiniteListState.isScrolledToTheEnd() && !state.isLoading) {
        onPaginate(
            Optional.present(Constants.ITEMS_LIMIT),
            Optional.present(shows.edges.last().cursor)
        )
    }
}

fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

@Composable
fun ProgressIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


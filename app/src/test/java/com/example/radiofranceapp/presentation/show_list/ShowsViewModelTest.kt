package com.example.radiofranceapp.presentation.show_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.apollographql.apollo3.api.Optional
import com.example.radiofranceapp.common.Constants.BRAND_ID_ARGUMENT
import com.example.radiofranceapp.common.Resource
import com.example.radiofranceapp.domain.model.Shows
import com.example.radiofranceapp.domain.repo.ShowRepository
import com.example.radiofranceapp.domain.usecases.GetShowsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ShowsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getShowsUseCase: GetShowsUseCase

    @Mock
    private lateinit var showRepository: ShowRepository

    private lateinit var viewModel: ShowsViewModel

    private val station = "example_station"

    @Before
    fun setup() {
        val savedStateHandle = SavedStateHandle().apply {
            set(BRAND_ID_ARGUMENT, station)
        }
        viewModel = ShowsViewModel(getShowsUseCase, savedStateHandle)
    }

    @Test
    fun `getShows should update state with success`() = runTest {
        // Mock data
        val shows = Shows(
            listOf(
                Shows.ShowEdge(
                    cursor = "1",
                    node = Shows.ShowEdge.Show(
                        id = "1",
                        title = "Show 1",
                        url = "https://example.com/show1",
                        standFirst = "First show"
                    )
                ),
                Shows.ShowEdge(
                    cursor = "2",
                    node = Shows.ShowEdge.Show(
                        id = "2",
                        title = "Show 2",
                        url = "https://example.com/show2",
                        standFirst = "Second show"
                    )
                ),
                Shows.ShowEdge(
                    cursor = "3",
                    node = Shows.ShowEdge.Show(
                        id = "3",
                        title = "Show 3",
                        url = "https://example.com/show3",
                        standFirst = "Third show"
                    )
                )
            )
        )

        val successResource = Resource.Success(shows)
        `when`(getShowsUseCase.invoke(station, Optional.present(100), Optional.absent())).thenReturn(flowOf(successResource))

        // Call the method
        viewModel.getShows(Optional.present(100), Optional.absent())

        // Assert the state
        assertEquals(shows, viewModel.state.value.shows)
        assertEquals(null, viewModel.state.value.error)
        assertEquals(false, viewModel.state.value.isLoading)
    }

    @Test
    fun `getShows should update state with error`() = runTest {
        // Mock data
        val errorMessage = "An unexpected error occurred"
        val errorResource = Resource.Error<Shows>(errorMessage)
        `when`(getShowsUseCase.invoke(station, Optional.present(100), Optional.absent())).thenReturn(flowOf(errorResource))

        // Call the method
        viewModel.getShows(Optional.present(100), Optional.absent())

        // Assert the state
        assertEquals(null, viewModel.state.value.shows)
        assertEquals(errorMessage, viewModel.state.value.error)
        assertEquals(false, viewModel.state.value.isLoading)
    }
}
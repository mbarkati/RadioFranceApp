package com.example.radiofranceapp.presentation.brand_list

import com.example.radiofranceapp.common.Resource
import com.example.radiofranceapp.domain.model.SimpleBrand
import com.example.radiofranceapp.domain.usecases.GetBrandsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class BrandsViewModelTest {

    private lateinit var viewModel: BrandsViewModel

    @Mock
    private lateinit var mockGetBrandsUseCase: GetBrandsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = BrandsViewModel(mockGetBrandsUseCase)
    }

    @Test
    fun `getBrands failed updates state with error message`() = runTest {
        // Arrange
        val errorMessage = "An error occurred"
        val errorResource = Resource.Error<List<SimpleBrand>>(errorMessage)
        `when`(mockGetBrandsUseCase.invoke()).thenReturn(flowOf(errorResource))

        // Act
        viewModel.getBrands()

        // Assert
        val state = viewModel.state.value
        assertEquals(true, state.isLoading)
        assertEquals(errorMessage, state.error)
        assertEquals(emptyList<SimpleBrand>(), state.brands)
    }

    @Test
    fun `getBrands succeeded updates state`() = runTest {
        // Arrange
        val brands = listOf(
            SimpleBrand("1", "Brand 1", "Description 1"),
            SimpleBrand("2", "Brand 2", "Description 2")
        )
        val successResource = Resource.Success(brands)
        `when`(mockGetBrandsUseCase.invoke()).thenReturn(flowOf(successResource))

        // Act
        viewModel.getBrands()

        // Assert
        val state = viewModel.state.value
        assertEquals(true, state.isLoading)
        assertEquals("", state.error)
        assertEquals(brands, state.brands)
    }
}
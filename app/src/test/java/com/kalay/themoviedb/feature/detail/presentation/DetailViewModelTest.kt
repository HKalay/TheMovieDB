package com.kalay.themoviedb.feature.detail.presentation

import com.kalay.themoviedb.core.util.MainDispatcherRule
import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.domain.enums.DetailType
import com.kalay.themoviedb.domain.model.remote.Detail
import com.kalay.themoviedb.domain.usecase.remote.detail.GetDetailMovieUseCase
import com.kalay.themoviedb.domain.usecase.remote.detail.GetDetailTvShowUseCase
import com.google.common.truth.Truth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getDetailMovieUseCase: GetDetailMovieUseCase
    private lateinit var getDetailTvShowUseCase: GetDetailTvShowUseCase

    @Before
    fun setUp() {
        getDetailMovieUseCase = mockk(relaxed = true)
        getDetailTvShowUseCase = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given initial state, When updateIsFavorite called with true, Then uiState isFavorite is true`() = runTest {
        // Given
        val viewModel = DetailViewModel(getDetailMovieUseCase, getDetailTvShowUseCase)

        // When
        viewModel.updateIsFavorite(true)

        // Then
        viewModel.uiState.value.apply {
            Truth.assertThat(isFavorite).isTrue()
        }
    }

    @Test
    fun `Given initial state, When updateIsFavorite called with false, Then uiState isFavorite is false`() = runTest {
        // Given
        val viewModel = DetailViewModel(getDetailMovieUseCase, getDetailTvShowUseCase)

        // When
        viewModel.updateIsFavorite(false)

        // Then
        viewModel.uiState.value.apply {
            Truth.assertThat(isFavorite).isFalse()
        }
    }

    @Test
    fun `Given fetchDetail returned error, When hideDialog called, Then detailResource is Empty`() = runTest {
        // Given
        coEvery { getDetailMovieUseCase(any()) } throws RuntimeException("network error")
        val viewModel = DetailViewModel(getDetailMovieUseCase, getDetailTvShowUseCase)
        val detail = Detail(id = 1, detailType = DetailType.MOVIE)

        // When
        viewModel.fetchDetail(detail)
        viewModel.hideDialog()

        // Then
        viewModel.uiState.value.apply {
            Truth.assertThat(detailResource).isInstanceOf(Resource.Empty::class.java)
        }
    }
}

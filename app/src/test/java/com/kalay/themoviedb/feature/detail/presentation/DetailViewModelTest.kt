package com.kalay.themoviedb.feature.detail.presentation

import com.kalay.themoviedb.core.util.MainDispatcherRule
import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.domain.enums.DetailType
import com.kalay.themoviedb.domain.model.remote.Detail
import com.kalay.themoviedb.domain.usecase.remote.detail.GetDetailMovieUseCase
import com.kalay.themoviedb.domain.usecase.remote.detail.GetDetailTvShowUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `updateIsFavorite true`() {
        val getDetailMovieUseCase = mockk<GetDetailMovieUseCase>(relaxed = true)
        val getDetailTvShowUseCase = mockk<GetDetailTvShowUseCase>(relaxed = true)
        val viewModel = DetailViewModel(getDetailMovieUseCase, getDetailTvShowUseCase)

        viewModel.updateIsFavorite(true)

        assertEquals(true, viewModel.uiState.value.isFavorite)
    }

    @Test
    fun `updateIsFavorite false`() {
        val getDetailMovieUseCase = mockk<GetDetailMovieUseCase>(relaxed = true)
        val getDetailTvShowUseCase = mockk<GetDetailTvShowUseCase>(relaxed = true)
        val viewModel = DetailViewModel(getDetailMovieUseCase, getDetailTvShowUseCase)

        viewModel.updateIsFavorite(false)

        assertEquals(false, viewModel.uiState.value.isFavorite)
    }

    @Test
    fun `hideDialog when Error then Empty`() = runTest {
        val getDetailMovieUseCase = mockk<GetDetailMovieUseCase>(relaxed = true)
        val getDetailTvShowUseCase = mockk<GetDetailTvShowUseCase>(relaxed = true)
        coEvery { getDetailMovieUseCase(any()) } throws RuntimeException("network error")
        val viewModel = DetailViewModel(getDetailMovieUseCase, getDetailTvShowUseCase)
        val detail = Detail(id = 1, detailType = DetailType.MOVIE)

        viewModel.fetchDetail(detail)
        viewModel.hideDialog()

        assertTrue(viewModel.uiState.value.detailResource is Resource.Empty)
    }
}

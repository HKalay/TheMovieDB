package com.kalay.themoviedb.feature.detail.presentation

import com.kalay.themoviedb.core.util.MainDispatcherRule
import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.domain.enums.DetailType
import com.kalay.themoviedb.domain.model.remote.Detail
import com.kalay.themoviedb.domain.usecase.remote.detail.GetDetailMovieUseCase
import com.kalay.themoviedb.domain.usecase.remote.detail.GetDetailTvShowUseCase
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.coVerify
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

    @RelaxedMockK
    lateinit var getDetailMovieUseCase: GetDetailMovieUseCase

    @RelaxedMockK
    lateinit var getDetailTvShowUseCase: GetDetailTvShowUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
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

    @Test
    fun `Given movie detail, When fetchDetail called, Then getDetailMovieUseCase invoked exactly once with detail id`() = runTest {
        // Given
        val expectedDetail = Detail(id = 42, detailType = DetailType.MOVIE)
        coEvery { getDetailMovieUseCase(42) } returns expectedDetail
        val viewModel = DetailViewModel(getDetailMovieUseCase, getDetailTvShowUseCase)

        // When
        viewModel.fetchDetail(expectedDetail)

        // Then
        coVerify(exactly = 1) { getDetailMovieUseCase(42) }
    }

    @Test
    fun `Given movie detail, When fetchDetail called, Then getDetailMovieUseCase receives captured id`() = runTest {
        // Given
        var capturedId: Int? = null
        val expectedDetail = Detail(id = 99, detailType = DetailType.MOVIE)
        coEvery { getDetailMovieUseCase(any()) } answers {
            capturedId = firstArg()
            expectedDetail
        }
        val viewModel = DetailViewModel(getDetailMovieUseCase, getDetailTvShowUseCase)

        // When
        viewModel.fetchDetail(expectedDetail)

        // Then
        Truth.assertThat(capturedId).isEqualTo(99)
    }
}

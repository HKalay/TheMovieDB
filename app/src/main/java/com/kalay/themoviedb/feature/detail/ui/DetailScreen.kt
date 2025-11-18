package com.kalay.themoviedb.feature.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kalay.themoviedb.core.theme.font.urbanistTypography
import com.kalay.themoviedb.core.ui.NetworkImage
import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.core.util.fromJson
import com.kalay.themoviedb.domain.model.remote.DetailDTO
import com.kalay.themoviedb.feature.detail.navigation.DetailScreenDestination
import com.kalay.themoviedb.feature.detail.presentation.DetailViewModel
import com.kalay.themoviedb.feature.detail.ui.component.DetailDialog
import com.kalay.themoviedb.feature.detail.ui.component.DetailScreenBackground
import com.kalay.themoviedb.feature.detail.ui.component.DetailScreenHeader
import com.kalay.themoviedb.feature.detail.ui.component.section.Genres
import com.kalay.themoviedb.feature.detail.ui.component.section.Overview
import com.kalay.themoviedb.feature.detail.ui.component.section.ReleaseDate
import com.kalay.themoviedb.feature.detail.ui.component.section.Vote
import com.kalay.themoviedb.feature.favorites.presentation.FavoriteViewModel


@Composable
fun DetailScreen(detailScreenDestination: DetailScreenDestination, navController: NavController) {

    val viewModel: DetailViewModel = hiltViewModel()
    val favoriteViewModel: FavoriteViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsState()
    val favoritesMap by favoriteViewModel.favoritesMap.collectAsState()

    val args = detailScreenDestination.discoverJson.fromJson<DetailDTO>()

    LaunchedEffect(Unit) {
        viewModel.fetchDetail(detailDTO = args)
        favoriteViewModel.syncFavoriteState(args)
    }

    LaunchedEffect(favoritesMap) {
        val isFavorite = favoritesMap[args.id] ?: false
        viewModel.updateIsFavorite(isFavorite)
    }

    DetailDialog(navController = navController, viewModel = viewModel)

    Box(modifier = Modifier.fillMaxSize()) {
        DetailScreenBackground(detailDTO = args)

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets.safeDrawing
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                DetailScreenHeader(
                    navController = navController,
                    isFavorite = uiState.isFavorite,
                    onToggleFavorite = {
                        favoriteViewModel.toggleFavorite(args)
                    }
                )

                when (val resource = uiState.detailResource) {
                    is Resource.Success -> {
                        val detail = resource.data

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 12.dp)
                                .padding(horizontal = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            item {
                                NetworkImage(
                                    imageUrl = detail.backdropPath,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(16f / 9f)
                                        .clip(RoundedCornerShape(12.dp))
                                )

                                Text(
                                    modifier = Modifier.padding(
                                        top = 12.dp
                                    ),
                                    text = detail.title.toString(),
                                    style = urbanistTypography().typography.headlineLarge.copy(
                                        color = Color.White
                                    )
                                )
                            }
                            item { Vote(detail = detail) }
                            item { ReleaseDate(detail = detail) }
                            item { Genres(detail = detail) }
                            item { Overview(detail = detail) }
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}
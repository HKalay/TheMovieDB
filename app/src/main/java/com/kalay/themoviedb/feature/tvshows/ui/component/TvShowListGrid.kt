package com.kalay.themoviedb.feature.tvshows.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kalay.themoviedb.core.theme.PrimaryColor
import com.kalay.themoviedb.core.ui.MovieCard
import com.kalay.themoviedb.core.util.toJson
import com.kalay.themoviedb.domain.mapper.toDetail
import com.kalay.themoviedb.domain.model.remote.Discover
import com.kalay.themoviedb.feature.detail.navigation.DetailScreenDestination
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun TvShowListGrid(
    navController: NavController,
    tvShows: List<Discover>,
    favoriteMap: Map<Int, Boolean>,
    onReachBottom: () -> Unit,
    updateFavoriteStatus: (Discover) -> Unit,
    onSyncFavorite: (Discover) -> Unit
) {
    val listState = rememberLazyGridState()

    LaunchedEffect(listState) {
        snapshotFlow {
            val total = listState.layoutInfo.totalItemsCount
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            total > 0 && lastVisible >= total - 2
        }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                onReachBottom()
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PrimaryColor)
            .padding(12.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tvShows) { tvShow ->
                LaunchedEffect(tvShow.id) {
                    onSyncFavorite(tvShow)
                }

                val isFavorite = favoriteMap[tvShow.id] ?: false

                MovieCard(
                    discover = tvShow.copy(isFavorite = isFavorite),
                    onClick = {
                        val discoverJson = tvShow.toDetail().toJson()
                        navController.navigate(DetailScreenDestination(discoverJson))
                    },
                    updateFavoriteStatus = {
                        updateFavoriteStatus(tvShow)
                    }
                )
            }
        }
    }
}
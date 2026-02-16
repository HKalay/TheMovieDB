package com.kalay.themoviedb.feature.favorites.ui.component

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kalay.themoviedb.core.theme.PrimaryColor
import com.kalay.themoviedb.core.ui.MovieCard
import com.kalay.themoviedb.core.util.toJson
import com.kalay.themoviedb.domain.mapper.toDetail
import com.kalay.themoviedb.domain.mapper.toDiscover
import com.kalay.themoviedb.domain.model.local.Favorite
import com.kalay.themoviedb.feature.detail.navigation.DetailScreenDestination

@Composable
fun FavoriteListGrid(
    navController: NavController,
    favorites: List<Favorite>,
    updateFavoriteStatus: (Int) -> Unit
) {
    val listState = rememberLazyGridState()

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
            items(favorites) { favorite ->
                MovieCard(
                    discover = favorite.toDiscover(),
                    onClick = {
                        val discoverJson = favorite
                            .toDetail()
                            .toJson()
                        navController.navigate(DetailScreenDestination(discoverJson))
                    },
                    updateFavoriteStatus = {
                        updateFavoriteStatus(favorite.id)
                    }
                )
            }
        }
    }
}
package com.kalay.themoviedb.core.navigation

import com.kalay.themoviedb.R

enum class BottomNavigationBarItemType(
    val route: String,
    val labelRes: Int,
    val iconRes: Int
) {
    MOVIES(
        route = "movies",
        labelRes = R.string.movies,
        iconRes = R.drawable.ic_movies
    ),
    TV_SHOWS(
        route = "tv",
        labelRes = R.string.tv_shows,
        iconRes = R.drawable.ic_tv_shows
    ),
    FAVORITES(
        route = "favorites",
        labelRes = R.string.favorites,
        iconRes = R.drawable.ic_favorite_un_selected
    )
}

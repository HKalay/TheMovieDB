package com.kalay.themoviedb.feature.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kalay.themoviedb.core.navigation.BottomNavigationBarItemType
import com.kalay.themoviedb.core.theme.PrimaryColor
import com.kalay.themoviedb.feature.favorites.ui.FavoritesScreen
import com.kalay.themoviedb.feature.movies.ui.MoviesScreen
import com.kalay.themoviedb.feature.tvshows.ui.TvShowsScreen

@Composable
fun MainScreen(navController: NavController) {
    val tabsNavController = rememberNavController()
    val currentBackStack by tabsNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            BottomNavigationBar(
                selectedTab = when (currentRoute) {
                    BottomNavigationBarItemType.MOVIES.route -> BottomNavigationBarItemType.MOVIES
                    BottomNavigationBarItemType.TV_SHOWS.route -> BottomNavigationBarItemType.TV_SHOWS
                    BottomNavigationBarItemType.FAVORITES.route -> BottomNavigationBarItemType.FAVORITES
                    else -> BottomNavigationBarItemType.MOVIES
                },
                onTabSelected = { tab ->
                    tabsNavController.navigate(tab.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(tabsNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = PrimaryColor)
                .padding(innerPadding)
        ) {
            NavHost(
                navController = tabsNavController,
                startDestination = BottomNavigationBarItemType.MOVIES.route
            ) {
                composable(BottomNavigationBarItemType.MOVIES.route) {
                    MoviesScreen(navController = navController)
                }
                composable(BottomNavigationBarItemType.TV_SHOWS.route) {
                    TvShowsScreen(navController = navController)
                }
                composable(BottomNavigationBarItemType.FAVORITES.route) {
                    FavoritesScreen(navController = navController)
                }
            }
        }
    }
}

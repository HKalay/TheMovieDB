package com.kalay.themoviedb.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kalay.themoviedb.feature.detail.navigation.DetailScreenDestination
import com.kalay.themoviedb.feature.detail.ui.DetailScreen
import com.kalay.themoviedb.feature.main.navigation.MainScreenDestination
import com.kalay.themoviedb.feature.main.ui.MainScreen
import com.kalay.themoviedb.feature.splash.navigation.SplashScreenDestination
import com.kalay.themoviedb.feature.splash.ui.SplashScreen

@Composable
fun AppNav() {
    val navController = rememberNavController()
    val duration = 700

    NavHost(
        navController = navController,
        startDestination = SplashScreenDestination
    ) {

        composable<SplashScreenDestination>(
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(duration)
                )
            }
        ) {
            SplashScreen(navController = navController)
        }

        composable<MainScreenDestination>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(duration)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(duration)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(duration)
                )
            }
        ) {
            MainScreen(navController = navController)
        }

        composable<DetailScreenDestination>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(duration)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(duration)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(duration)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(duration)
                )
            }
        ) { backStackEntry ->
            val args = backStackEntry.toRoute<DetailScreenDestination>()
            DetailScreen(detailScreenDestination = args, navController = navController)
        }
    }
}
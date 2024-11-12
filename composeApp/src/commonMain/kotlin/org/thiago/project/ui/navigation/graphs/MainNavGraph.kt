package org.thiago.project.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.thiago.project.ui.bookmark.BookmarkScreen
import org.thiago.project.ui.headline.HeadlineScreen
import org.thiago.project.ui.navigation.Graph
import org.thiago.project.ui.navigation.MainRouteScreen
import org.thiago.project.ui.search.SearchScreen


@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    homeNavController: NavHostController,
    innerPadding: PaddingValues,

) {
    NavHost(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        navController = homeNavController,
        route = Graph.MainScreenGraph,
        startDestination = MainRouteScreen.Headline.route,

    ) {

        composable(route = MainRouteScreen.Headline.route) {
            HeadlineScreen()
        }

        composable(route = MainRouteScreen.Search.route) {
            SearchScreen()
        }

        composable(route = MainRouteScreen.Bookmark.route) {
            BookmarkScreen()
        }
    }

}
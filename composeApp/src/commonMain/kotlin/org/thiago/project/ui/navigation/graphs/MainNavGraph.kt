package org.thiago.project.ui.navigation.graphs


import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.thiago.project.ui.bookmark.BookmarkScreen
import org.thiago.project.ui.headline.HeadlineScreen
import org.thiago.project.ui.navigation.Graph
import org.thiago.project.ui.navigation.MainRouteScreen
import org.thiago.project.ui.search.SearchScreen


fun NavGraphBuilder.mainNavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues
) {
    navigation(
        startDestination = MainRouteScreen.Headline.route,
        route = Graph.MainScreenGraph
    ) {
        composable(route = MainRouteScreen.Headline.route) {
            HeadlineScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable(route = MainRouteScreen.Search.route) {
            SearchScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable(route = MainRouteScreen.Bookmark.route) {
            BookmarkScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
    }

}
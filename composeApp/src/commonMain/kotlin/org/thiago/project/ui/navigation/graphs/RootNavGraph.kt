package org.thiago.project.ui.navigation.graphs


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.json.Json
import org.thiago.project.data.model.Article
import org.thiago.project.di.ArticleDetailScreen
import org.thiago.project.ui.navigation.Graph
import org.thiago.project.ui.navigation.NewsRouteScreen
import org.thiago.project.ui.navigation.SettingRouteScreen
import org.thiago.project.ui.setting.SettingScreen
import org.thiago.project.ui.setting.SettingViewModel


@Composable
fun RootNavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues,
    settingViewModel: SettingViewModel
) {
    NavHost(
        navController = rootNavController,
        startDestination = Graph.MainScreenGraph,
    ) {
        mainNavGraph(rootNavController = rootNavController, innerPadding = innerPadding)
        composable(
            route = NewsRouteScreen.NewsDetail.route,
        ) {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")
                ?.let { article ->
                    val currentArticle: Article = Json.decodeFromString(article)
                    ArticleDetailScreen(rootNavController, currentArticle)
                }
        }
        composable(
            route = SettingRouteScreen.SettingDetail.route,
        ) {
            SettingScreen(navController = rootNavController, settingViewModel)
        }
    }
}
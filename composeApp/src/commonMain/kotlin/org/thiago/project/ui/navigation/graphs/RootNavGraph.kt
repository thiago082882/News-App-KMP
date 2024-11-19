package org.thiago.project.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.json.Json
import org.thiago.project.data.database.NewsDatabase
import org.thiago.project.data.model.Article
import org.thiago.project.ui.MainScreen
import org.thiago.project.ui.article_detail.ArticleDetailScreen
import org.thiago.project.ui.navigation.Graph
import org.thiago.project.ui.navigation.NewsRouteScreen
import org.thiago.project.ui.navigation.SettingRouteScreen
import org.thiago.project.ui.setting.SettingScreen
import org.thiago.project.ui.setting.SettingViewModel


@Composable
fun RootNavGraph(newsDatabase: NewsDatabase, settingViewModel: SettingViewModel) {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        route = Graph.RootGraph,
        startDestination = Graph.MainScreenGraph,
    ) {
        composable(route = Graph.MainScreenGraph){
            MainScreen(rootNavController,newsDatabase)
        }
        composable(
            route = NewsRouteScreen.NewsDetail.route,
        ) {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let { article ->
                val currentArticle: Article = Json.decodeFromString(article)
                ArticleDetailScreen(rootNavController, newsDatabase, currentArticle)
            }
        }
        composable(
            route = SettingRouteScreen.SettingDetail.route,
        ) {
            SettingScreen(navController = rootNavController,settingViewModel)
        }
    }
}
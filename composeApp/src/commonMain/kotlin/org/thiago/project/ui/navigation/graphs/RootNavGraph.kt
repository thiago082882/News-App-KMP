package org.thiago.project.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.json.Json
import org.thiago.project.ui.MainScreen
import org.thiago.project.ui.navigation.Graph
import org.thiago.project.ui.navigation.SettingRouteScreen
import org.thiago.project.ui.setting.SettingScreen


@Composable
fun RootNavGraph() {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        route = Graph.RootGraph,
        startDestination = Graph.MainScreenGraph,
    ) {
        composable(route = Graph.MainScreenGraph){
            MainScreen(rootNavController)
        }
//        composable(
//            route = NewsRouteScreen.NewsDetail.route,
//        ) {
//            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let { article ->
//
//            }
//        }
        composable(
            route = SettingRouteScreen.Setting.route,
        ) {
            SettingScreen(rootNavController)
        }
    }
}
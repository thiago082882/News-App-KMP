package org.thiago.project.ui.navigation


object Graph {
    const val RootGraph = "rootScreenGraph"
    const val MainScreenGraph = "mainScreenGraph"
}

sealed class MainRouteScreen(var route: String) {

    object Headline : MainRouteScreen("headline")
    object Search : MainRouteScreen("search")
    object Bookmark : MainRouteScreen("bookmark")
}


sealed class SettingRouteScreen(var route: String) {
    object Setting : SettingRouteScreen("setting")
}
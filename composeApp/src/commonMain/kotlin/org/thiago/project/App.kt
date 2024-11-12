package org.thiago.project

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.thiago.project.theme.NewsAppTheme
import org.thiago.project.ui.navigation.graphs.RootNavGraph

@Composable
@Preview
fun App() {
    NewsAppTheme(darkTheme = false) {
        RootNavGraph()
    }

}
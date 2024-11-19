package org.thiago.project.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.thiago.project.data.model.Article
import org.thiago.project.theme.xLargePadding
import org.thiago.project.ui.navigation.NewsRouteScreen
import org.thiago.project.utils.Type
import org.thiago.project.utils.articles
import org.thiago.project.utils.getRandomId
import org.thiago.project.utils.getType



@Composable
fun ArticleListScreen(articleList: List<Article>,navController: NavController) {
    val isDesktop = remember {
        getType() == Type.Desktop
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isDesktop) 3 else 1),
        verticalArrangement = Arrangement.spacedBy(xLargePadding),
        horizontalArrangement = Arrangement.spacedBy(xLargePadding),
        contentPadding = PaddingValues(xLargePadding),
    ){
        items(articleList, key = {
            it.publishedAt+ getRandomId()
        }) { item ->
            ArticleItem(article = item, onClick = {
                val articleStr = Json.encodeToString(item)
                navController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("article",articleStr)
                }
               navController.navigate(NewsRouteScreen.NewsDetail.route)

            })
        }
    }

}
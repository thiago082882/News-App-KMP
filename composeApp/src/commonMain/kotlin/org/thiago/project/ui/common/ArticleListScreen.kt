package org.thiago.project.ui.common


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.thiago.project.data.model.Article
import org.thiago.project.theme.cardMinSize
import org.thiago.project.theme.mediumPadding
import org.thiago.project.ui.navigation.NewsRouteScreen
import org.thiago.project.utils.randomUUIDStr


@Composable
fun ArticleListScreen(
    articleList: List<Article>,
    rootNavController: NavController
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(cardMinSize),
        verticalItemSpacing = mediumPadding,
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding),
    ) {
        items(articleList, key = {
            it.publishedAt + randomUUIDStr()
        }) { item ->
            ArticleItem(article = item, onClick = {

                val articleStr = Json.encodeToString(item)
                rootNavController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("article", articleStr)
                }
                rootNavController.navigate(NewsRouteScreen.NewsDetail.route)
            })
        }
    }

}
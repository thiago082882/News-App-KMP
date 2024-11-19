package org.thiago.project.ui.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_bookmarks
import org.jetbrains.compose.resources.stringResource
import org.thiago.project.data.database.NewsDatabase
import org.thiago.project.ui.common.ArticleListScreen
import org.thiago.project.ui.common.EmptyContent
import org.thiago.project.ui.common.ShimmerEffect

@Composable
fun BookmarkScreen(
    navController: NavController, newsDatabase: NewsDatabase

) {
    val bookmarkViewModel = viewModel {
        BookmarkViewModel(newsDatabase)
    }
    val uiState by bookmarkViewModel.bookmarkNewsStateFlow.collectAsState()

    uiState.DisplayResult(onLoading = {
        ShimmerEffect()
    }, onSuccess = { articleList ->
        if (articleList.isEmpty()) {
            EmptyContent(message = stringResource(Res.string.no_bookmarks), icon = Res.drawable.ic_network_error, onRetryClick = null)
        } else {
            ArticleListScreen(articleList,navController)
        }
    }, onError = {
        EmptyContent(message = it, icon = Res.drawable.ic_network_error, onRetryClick = {
            bookmarkViewModel.getArticles()
        })
    })

}
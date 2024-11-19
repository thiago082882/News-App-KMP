package org.thiago.project.ui.headline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news

import org.jetbrains.compose.resources.stringResource
import org.thiago.project.ui.common.ArticleListScreen
import org.thiago.project.ui.common.EmptyContent
import org.thiago.project.ui.common.ShimmerEffect


@Composable
fun HeadlineScreen(navController: NavController) {

    val headlineViewModel = viewModel { HeadlineViewModel() }
    val uiState by headlineViewModel.newsStateFlow.collectAsState()
    uiState.DisplayResult(
        onLoading = {
            ShimmerEffect()
        },
        onSuccess = { articleList ->
            if (articleList.isEmpty()) {
                EmptyContent(message = stringResource(Res.string.no_news), icon = Res.drawable.ic_network_error, onRetryClick = null)
            } else {
                ArticleListScreen(articleList,navController)
            }
        },
        onError = {
            EmptyContent(message = it, icon = Res.drawable.ic_network_error, onRetryClick = {
                headlineViewModel.getHeadlines()
            })
        }
    )
}
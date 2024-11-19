package org.thiago.project.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.thiago.project.theme.mediumPadding
import org.thiago.project.ui.common.ArticleListScreen
import org.thiago.project.ui.search.components.SearchBar

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.lifecycle.viewmodel.compose.viewModel
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.type_to_search

import org.jetbrains.compose.resources.stringResource
import org.thiago.project.ui.common.EmptyContent
import org.thiago.project.ui.common.ShimmerEffect


@Composable
fun SearchScreen(
    navController: NavController,
) {
    val searchViewModel = viewModel { SearchViewModel() }
    val uiState by searchViewModel.newsStateFlow.collectAsState()

    val searchQuery by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(mediumPadding)
    ) {
        SearchBar(
            modifier = Modifier.padding(horizontal = mediumPadding),
            text = searchQuery,
            onSearch = { query ->
                if (query.trim().isNotEmpty()){
                    searchViewModel.searchQueryNews(query.trim())
                }
            },
        )
        uiState.DisplayResult(
            onIdle = {
                EmptyContent(
                    message = stringResource(Res.string.type_to_search),
                    icon = Res.drawable.ic_browse,
                    onRetryClick = null
                )
            },
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { articleList ->
                if (articleList.isEmpty()) {
                    EmptyContent(
                        message = stringResource(Res.string.type_to_search),
                        icon = Res.drawable.ic_browse,
                        onRetryClick = null
                    )
                } else {
                    ArticleListScreen(articleList,navController)
                }
            },
            onError = {
                EmptyContent(message = it, icon = Res.drawable.ic_network_error, onRetryClick = {
                    if (searchQuery.trim().isNotEmpty()){
                        searchViewModel.searchQueryNews(searchQuery.trim())
                    }
                })
            }
        )
    }
}
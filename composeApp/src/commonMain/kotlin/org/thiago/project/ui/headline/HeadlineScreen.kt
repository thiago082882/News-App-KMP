package org.thiago.project.ui.headline


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.thiago.project.theme.xSmallPadding
import org.thiago.project.ui.common.ArticleListScreen
import org.thiago.project.ui.common.EmptyContent
import org.thiago.project.ui.common.ShimmerEffect
import org.thiago.project.ui.navigation.SettingRouteScreen
import org.thiago.project.utils.categoryList
import org.thiago.project.utils.navigationItemsLists

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadlineScreen(
    rootNavController: NavController, paddingValues: PaddingValues
) {

    val headlineViewModel = koinViewModel<HeadlineViewModel>()
    val originDirection = LocalLayoutDirection.current

    val uiState by headlineViewModel.newsStateFlow.collectAsState()
    Column(
        Modifier.fillMaxSize().padding(
            start = paddingValues.calculateStartPadding(originDirection),
            end = paddingValues.calculateEndPadding(originDirection),
            bottom = paddingValues.calculateBottomPadding(),
        )
    ) {
        TopAppBar(title = {
            Text(
                text = stringResource(navigationItemsLists[0].title),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }, actions = {
            IconButton(onClick = {
                rootNavController.navigate(SettingRouteScreen.SettingDetail.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                )
            }
        })
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = xSmallPadding),
            horizontalArrangement = Arrangement.spacedBy(
                xSmallPadding,
                Alignment.CenterHorizontally
            )
        ) {
            items(categoryList, key = { it }) { category ->
                FilterChip(
                    selected = headlineViewModel.category == category,
                    onClick = {
                        headlineViewModel.category = category
                        headlineViewModel.getHeadlines(headlineViewModel.category)
                    }, label = {
                        Text(category)
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    )
                )
            }
        }
        uiState.DisplayResult(
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { articleList ->
                if (articleList.isEmpty()) {
                    EmptyContent(
                        message = stringResource(Res.string.no_news),
                        icon = Res.drawable.ic_browse,
                        onRetryClick = {
                            headlineViewModel.getHeadlines(headlineViewModel.category)
                        }
                    )
                } else {
                    ArticleListScreen(
                        articleList = articleList,
                        rootNavController = rootNavController
                    )
                }
            },
            onError = {
                EmptyContent(
                    message = it,
                    icon = Res.drawable.ic_network_error,
                    onRetryClick = {
                        headlineViewModel.getHeadlines(headlineViewModel.category)
                    }
                )
            }
        )
    }
}
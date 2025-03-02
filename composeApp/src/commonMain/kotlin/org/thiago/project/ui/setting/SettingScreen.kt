package org.thiago.project.ui.setting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.cancel
import kmp_news_app.composeapp.generated.resources.delete
import kmp_news_app.composeapp.generated.resources.delete_bookmark
import kmp_news_app.composeapp.generated.resources.delete_bookmark_description
import kmp_news_app.composeapp.generated.resources.go_back
import kmp_news_app.composeapp.generated.resources.ic_delete
import kmp_news_app.composeapp.generated.resources.ic_light_mode
import kmp_news_app.composeapp.generated.resources.setting
import kmp_news_app.composeapp.generated.resources.theme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.thiago.project.ui.setting.component.SettingItem
import org.thiago.project.ui.setting.component.ThemeSelectionDialog
import org.thiago.project.utils.Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController, settingViewModel: SettingViewModel) {

    var showThemeSelectionDialog by remember { mutableStateOf(false) }
    val isDarkModeEnabled by settingViewModel.isDarkModeEnabled.collectAsState()
    var showDeleteBookmarkArticleDialog by remember { mutableStateOf(false) }

    when {
        showDeleteBookmarkArticleDialog -> {

            AlertDialog(
                onDismissRequest = { showDeleteBookmarkArticleDialog = false },
                title = { Text(stringResource(Res.string.delete_bookmark)) },
                text = { Text(stringResource(Res.string.delete_bookmark_description)) },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = stringResource(Res.string.delete_bookmark)
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            settingViewModel.deleteHistory()
                            showDeleteBookmarkArticleDialog = false
                        }
                    ) {
                        Text(stringResource(Res.string.delete))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteBookmarkArticleDialog = false }) {
                        Text(stringResource(Res.string.cancel))
                    }
                }
            )
        }

        showThemeSelectionDialog -> {
            ThemeSelectionDialog(
                onThemeChange = { theme ->
                    settingViewModel.changeDarkMode(theme == Theme.Dark)
                    showThemeSelectionDialog = false
                },
                onDismissRequest = { showThemeSelectionDialog = false },
                currentTheme = if (isDarkModeEnabled) Theme.Dark else Theme.Light
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.setting)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.go_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                SettingItem(
                    onClick = {
                        showThemeSelectionDialog = true
                    },
                    painter = painterResource(Res.drawable.ic_light_mode),
                    itemName = stringResource(Res.string.theme)
                )
            }
            item {
                SettingItem(
                    onClick = {
                        showDeleteBookmarkArticleDialog = true
                    },
                    painter = painterResource(Res.drawable.ic_delete),
                    itemName = stringResource(Res.string.delete_bookmark),
                    itemColor = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
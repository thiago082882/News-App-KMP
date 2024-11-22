package org.thiago.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.thiago.project.theme.NewsAppTheme
import org.thiago.project.ui.MainScreen
import org.thiago.project.ui.setting.SettingViewModel

@Composable
@Preview
fun App() {
    KoinContext {
        val settingViewModel = koinViewModel<SettingViewModel>()
        val currentTheme by settingViewModel.currentTheme.collectAsState()
        NewsAppTheme(currentTheme) {
            MainScreen(settingViewModel)
        }
    }
}
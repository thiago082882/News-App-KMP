package org.thiago.project.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.thiago.project.data.database.NewsDatabase
import org.thiago.project.data.repository.LocalNewsRepository
import org.thiago.project.utils.AppPreferencesImpl


class SettingViewModel(
    private val appPreferencesImpl: AppPreferencesImpl,
    newsDatabase: NewsDatabase
) : ViewModel() {

    private val localNewsRepository = LocalNewsRepository(newsDatabase.newsDao())


    private val _isDarkModeEnabled = MutableStateFlow(false)
    val isDarkModeEnabled = _isDarkModeEnabled.asStateFlow()

    fun deleteHistory() = viewModelScope.launch(Dispatchers.IO) {
        localNewsRepository.deleteAllBookmark()
    }

    private val _isInit = MutableStateFlow(false)
    val isInit = _isInit.asStateFlow()


    init {
        isDarkModeEnabled()
        _isInit.value = true
    }

    private fun isDarkModeEnabled() = viewModelScope.launch(Dispatchers.IO) {
        _isDarkModeEnabled.value = appPreferencesImpl.isDarkModeEnabled()
    }

    fun changeDarkMode(isEnabled: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        appPreferencesImpl.changeDarkMode(isEnabled)
        _isDarkModeEnabled.value = isEnabled
    }
}
package org.thiago.project.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.thiago.project.data.repository.LocalNewsRepository
import org.thiago.project.utils.AppPreferences

class SettingViewModel(
    private val appPreferences: AppPreferences,
    private val localNewsRepository: LocalNewsRepository
) : ViewModel() {

    private val _currentTheme: MutableStateFlow<String?> = MutableStateFlow(null)
    val currentTheme = _currentTheme.asStateFlow()

    fun deleteHistory() = viewModelScope.launch(Dispatchers.IO) {
        localNewsRepository.deleteAllBookmark()
    }

    init {
        currentThemeGet()
    }

    private fun currentThemeGet() = runBlocking {
        _currentTheme.value = appPreferences.getTheme()
    }

    fun changeThemeMode(value: String) = viewModelScope.launch(Dispatchers.IO) {
        appPreferences.changeThemeMode(value)
        _currentTheme.value = value
    }
}
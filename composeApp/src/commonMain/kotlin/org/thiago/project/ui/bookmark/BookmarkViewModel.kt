package org.thiago.project.ui.bookmark


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.thiago.project.data.model.Article
import org.thiago.project.data.repository.LocalNewsRepository
import org.thiago.project.utils.Resource

class BookmarkViewModel(
    private val localNewsRepository: LocalNewsRepository
) : ViewModel() {

    private val _bookmarkNewsStateFlow =
        MutableStateFlow<Resource<List<Article>>>(Resource.Loading)
    val bookmarkNewsStateFlow: StateFlow<Resource<List<Article>>>
        get() = _bookmarkNewsStateFlow

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            _bookmarkNewsStateFlow.emit(Resource.Loading)
            localNewsRepository.getArticles().catch {
                it.printStackTrace()
                _bookmarkNewsStateFlow.emit(Resource.Error(it.message.toString()))
            }.collect { result ->
                _bookmarkNewsStateFlow.emit(Resource.Success(result))
            }
        }
    }
}
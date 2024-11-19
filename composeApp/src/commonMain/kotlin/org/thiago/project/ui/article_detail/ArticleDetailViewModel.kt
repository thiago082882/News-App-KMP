package org.thiago.project.ui.article_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.thiago.project.data.database.NewsDatabase
import org.thiago.project.data.model.Article
import org.thiago.project.data.repository.LocalNewsRepository

class ArticleDetailViewModel(
    newsDatabase: NewsDatabase
) : ViewModel() {

    private val localNewsRepository = LocalNewsRepository(newsDatabase.newsDao())
    var isBookmarked by mutableStateOf(false)

    fun isArticleBookmark(currentArticle: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            currentArticle.publishedAt.let {
                localNewsRepository.getArticle(it)?.let {
                    isBookmarked = true
                }
            }
        }
    }

    fun bookmarkArticle(currentArticle: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isBookmarked) {
                localNewsRepository.upsertArticle(currentArticle)
            } else {
                localNewsRepository.deleteArticle(currentArticle)
            }
            isBookmarked = !isBookmarked
        }
    }

}
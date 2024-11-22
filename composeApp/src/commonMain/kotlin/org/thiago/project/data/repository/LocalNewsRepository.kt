package org.thiago.project.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn
import org.thiago.project.data.database.NewsDao
import org.thiago.project.data.model.Article


class LocalNewsRepository(
    private val newsDao: NewsDao
) {
    suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }
    suspend fun deleteAllBookmark() {
        newsDao.deleteAllBookmark()
    }

    fun getArticles() = newsDao.getArticles().flowOn(Dispatchers.IO)

    suspend fun getArticle(articleId: String): Article? {
        return newsDao.getArticle(articleId = articleId)
    }
}
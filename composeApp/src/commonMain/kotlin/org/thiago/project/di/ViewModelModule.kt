package org.thiago.project.di


import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.thiago.project.ui.article_detail.ArticleDetailViewModel
import org.thiago.project.ui.bookmark.BookmarkViewModel
import org.thiago.project.ui.headline.HeadlineViewModel
import org.thiago.project.ui.search.SearchViewModel
import org.thiago.project.ui.setting.SettingViewModel

val viewmodelModule = module {
    viewModelOf(::HeadlineViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::ArticleDetailViewModel)
    viewModelOf(::SettingViewModel)
}
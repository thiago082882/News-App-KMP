package org.thiago.project.di


import org.koin.dsl.module
import org.thiago.project.data.database.NewsDatabase
import org.thiago.project.data.repository.LocalNewsRepository
import org.thiago.project.data.repository.OnlineNewsRepository

val repositoryModule = module {
    single {
        LocalNewsRepository(get<NewsDatabase>().newsDao())
    }
    single {
        OnlineNewsRepository(get())
    }
}
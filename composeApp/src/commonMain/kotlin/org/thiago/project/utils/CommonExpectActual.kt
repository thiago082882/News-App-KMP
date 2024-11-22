package org.thiago.project.utils

import org.thiago.project.data.database.NewsDatabase
import androidx.compose.runtime.Composable
import androidx.room.RoomDatabase


expect fun shareLink(url: String)

expect fun randomUUIDStr():String

expect fun getType():Type

@Composable
expect fun getScreenSize():Size

expect fun getDatabaseBuilder() : RoomDatabase.Builder<NewsDatabase>
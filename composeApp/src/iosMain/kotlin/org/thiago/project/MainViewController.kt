package org.thiago.project

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import androidx.room.Room
import androidx.room.RoomDatabase
import org.thiago.project.data.database.NewsDatabase
import org.thiago.project.data.database.instantiateImpl
import org.thiago.project.utils.DB_Name
import platform.Foundation.NSHomeDirectory
import utils.DB_Name

fun MainViewController() = ComposeUIViewController {
    val database = remember {
        getDatabaseBuilder()
    }
    App(database) }

fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DB_Name"
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFilePath,
        factory =  { NewsDatabase::class.instantiateImpl() }
    )
}
package org.thiago.project

import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.room.Room
import androidx.room.RoomDatabase
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.logo
import org.thiago.project.data.database.NewsDatabase
import org.thiago.project.utils.DB_Name
import java.awt.Dimension
import java.io.File

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, state = WindowState(
            position = WindowPosition(Alignment.Center)
        ), title = "News KMP APP",
        icon = org.jetbrains.compose.resources.painterResource(Res.drawable.logo)
    ) {
        window.minimumSize = Dimension(1280, 768)
        val database = remember { getDatabaseBuilder() }
        App(database)
    }
}

fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_Name)
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFile.absolutePath,
    )
}
package org.thiago.project

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.room.Room
import androidx.room.RoomDatabase
import org.thiago.project.data.database.NewsDatabase
import org.thiago.project.utils.DB_Name

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        MyApp.activity = this
        val database = getDatabaseBuilder(applicationContext)
        setContent {
            enableEdgeToEdge(
                SystemBarStyle.dark(MaterialTheme.colorScheme.onSurface.toArgb()),
                SystemBarStyle.dark(MaterialTheme.colorScheme.onSurface.toArgb()),
            )
            App(database)
        }
    }
}

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<NewsDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(DB_Name)
    return Room.databaseBuilder<NewsDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
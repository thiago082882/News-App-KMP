package org.thiago.project

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.logo
import org.thiago.project.di.initKoin
import java.awt.Dimension

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication, state = WindowState(
            position = WindowPosition(Alignment.Center)
        ),   title = "News KMP APP",
        icon = org.jetbrains.compose.resources.painterResource(Res.drawable.logo)
    ) {
        window.minimumSize = Dimension( 640, 480)
        App()
    }
}
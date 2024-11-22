package org.thiago.project

import androidx.compose.ui.window.ComposeUIViewController
import org.thiago.project.di.initKoin


fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}
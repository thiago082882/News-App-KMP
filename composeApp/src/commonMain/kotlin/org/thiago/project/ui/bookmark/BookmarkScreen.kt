package org.thiago.project.ui.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun BookmarkScreen() {
    Box(){
        Text("BookmarkScreen", fontSize = 32.sp, modifier = Modifier.align(Alignment.Center))
    }
}
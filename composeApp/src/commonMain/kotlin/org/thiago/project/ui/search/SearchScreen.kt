package org.thiago.project.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


@Composable
fun SearchScreen() {
    Box(){
        Text("SearchScreen", fontSize = 32.sp, modifier = Modifier.align(Alignment.Center))
    }
}
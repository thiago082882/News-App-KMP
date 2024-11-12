package org.thiago.project.ui.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SettingScreen(
    rootNavController: NavHostController
) {
    Box(Modifier.fillMaxSize()) {
        Button(
            onClick = {
             rootNavController.navigateUp()
            }
        ) {
            Text("Back")
        }
        Text("Setting", fontSize = 32.sp, modifier = Modifier.align(Alignment.Center))
    }
}
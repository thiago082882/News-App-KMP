package org.thiago.project.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.stringResource
import org.thiago.project.data.database.NewsDatabase
import org.thiago.project.ui.navigation.NewsBottomNavigation
import org.thiago.project.ui.navigation.SettingRouteScreen
import org.thiago.project.ui.navigation.graphs.MainNavGraph
import org.thiago.project.utils.bottomNavigationItemsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    rootNavHostController: NavHostController,
    newsDatabase: NewsDatabase
) {
    val homeNavController = rememberNavController()
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry?.destination?.route
        }
    }
    val topBarTitle by remember(currentRoute) {
        derivedStateOf {
            if (currentRoute != null) {
                bottomNavigationItemsList[bottomNavigationItemsList.indexOfFirst {
                    it.route == currentRoute
                }].title
            } else {
                bottomNavigationItemsList[0].title
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(topBarTitle),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                actions = {
                    IconButton(onClick = {
                        rootNavHostController.navigate(SettingRouteScreen.SettingDetail.route)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = null,
                        )
                    }
                }
            )
        },
        bottomBar = {
            NewsBottomNavigation(
                items = bottomNavigationItemsList,
                currentRoute = currentRoute,
                onItemClick = { currentNavigationItem ->
                    homeNavController.navigate(currentNavigationItem.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        homeNavController.graph.startDestinationRoute?.let { startDestinationRoute ->
                            // Pop up to the start destination, clearing the back stack
                            popUpTo(startDestinationRoute) {
                                // Save the state of popped destinations
                                saveState = true
                            }
                        }

                        // Configure navigation to avoid multiple instances of the same destination
                        launchSingleTop = true

                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        MainNavGraph(
            rootNavHostController,
            homeNavController, innerPadding, newsDatabase
        )
    }

}
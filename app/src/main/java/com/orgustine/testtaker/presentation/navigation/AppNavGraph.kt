package com.orgustine.testtaker.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.orgustine.testtaker.presentation.home.HomeScreen
import com.orgustine.testtaker.presentation.practice.PracticeScreen
import com.orgustine.testtaker.presentation.practice.SummaryScreen
import com.orgustine.testtaker.presentation.topics.TopicsListScreen
import com.orgustine.testtaker.util.HOME_ROUTE
import com.orgustine.testtaker.util.PRACTICE_ROUTE
import com.orgustine.testtaker.util.SCORE
import com.orgustine.testtaker.util.SUMMARY_ROUTE
import com.orgustine.testtaker.util.TOPIC
import com.orgustine.testtaker.util.TOPICS_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = SnackbarHostState())
        },
        topBar = {
            if (currentRoute == TOPICS_ROUTE) {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "back arrow"
                            )
                        }
                    }
                )
            }
        }
        /* floatingActionButton = {
             AppFAB(currentRoute) {
                 navController.navigate(TOPICS_ROUTE)
             }
         }*/
    ) { innerPadding ->

        NavHost(
            startDestination = HOME_ROUTE,
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(HOME_ROUTE) {
                HomeScreen {
                    navController.navigate(it)
                }
            }
            composable(
                route = "$PRACTICE_ROUTE/{$TOPIC}",
                arguments = listOf(navArgument(TOPIC) { type = NavType.StringType })
            ) { backStackEntry ->
                PracticeScreen(
                    topic = backStackEntry.arguments?.getString(TOPIC),
                    onNavigate = { navController.navigate(it) }
                )
            }
            composable(
                route = "$SUMMARY_ROUTE/{$SCORE}",
                arguments = listOf(navArgument(SCORE) { type = NavType.IntType })
            ) { backStackEntry ->
                SummaryScreen(
                    score = backStackEntry.arguments?.getInt(SCORE),
                    onNavigate = { navController.navigate(HOME_ROUTE) }
                )
            }
            composable(TOPICS_ROUTE) {
                TopicsListScreen()
            }
        }

    }
}
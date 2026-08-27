package com.example.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.data.model.BelkuchiCategory
import com.example.ui.screens.AIChatScreen
import com.example.ui.screens.AboutBelkuchiScreen
import com.example.ui.screens.CategoriesScreen
import com.example.ui.screens.EmergencyScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MapScreen
import com.example.ui.viewmodel.BelkuchiViewModel

sealed class Screen(
    val route: String,
    val titleBangla: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Home : Screen("home", "হোম", Icons.Filled.Home, Icons.Outlined.Home)
    object AIChat : Screen("ai_chat", "AI চ্যাট", Icons.Filled.AutoAwesome, Icons.Outlined.AutoAwesome)
    object Map : Screen("map", "ম্যাপ", Icons.Filled.Map, Icons.Outlined.Map)
    object Emergency : Screen("emergency", "জরুরি", Icons.Filled.Phone, Icons.Outlined.Phone)
    object Categories : Screen("categories", "ডিরেক্টরি", Icons.Filled.Category, Icons.Outlined.Category)
    object About : Screen("about", "পরিচিতি", Icons.Filled.Info, Icons.Outlined.Info)
}

@Composable
fun AppNavigation(
    viewModel: BelkuchiViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val snackbarHostState = remember { SnackbarHostState() }
    val toastMessage by viewModel.uiToastMessage.collectAsStateWithLifecycle()

    var activeChatPrompt by remember { mutableStateOf<String?>(null) }
    var activeCategory by remember { mutableStateOf<BelkuchiCategory?>(null) }

    LaunchedEffect(toastMessage) {
        toastMessage?.let { msg ->
            snackbarHostState.showSnackbar(
                message = msg,
                duration = SnackbarDuration.Short
            )
            viewModel.clearToast()
        }
    }

    val bottomNavItems = listOf(
        Screen.Home,
        Screen.AIChat,
        Screen.Map,
        Screen.Emergency,
        Screen.Categories,
        Screen.About
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                bottomNavItems.forEach { screen ->
                    val isSelected = currentRoute == screen.route
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) screen.selectedIcon else screen.unselectedIcon,
                                contentDescription = screen.titleBangla
                            )
                        },
                        label = {
                            Text(
                                text = screen.titleBangla,
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_item_${screen.route}")
                    )
                }
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = viewModel,
                    onNavigateToChatWithPrompt = { prompt ->
                        activeChatPrompt = prompt
                        navController.navigate(Screen.AIChat.route)
                    },
                    onNavigateToCategory = { category ->
                        viewModel.selectCategory(category)
                        navController.navigate(Screen.Categories.route)
                    },
                    onNavigateToMap = {
                        navController.navigate(Screen.Map.route)
                    },
                    onNavigateToEmergency = {
                        navController.navigate(Screen.Emergency.route)
                    }
                )
            }

            composable(Screen.AIChat.route) {
                AIChatScreen(
                    viewModel = viewModel,
                    initialPrompt = activeChatPrompt
                )
                // Clear active prompt after consumption
                LaunchedEffect(Unit) {
                    activeChatPrompt = null
                }
            }

            composable(Screen.Map.route) {
                MapScreen(viewModel = viewModel)
            }

            composable(Screen.Emergency.route) {
                EmergencyScreen(viewModel = viewModel)
            }

            composable(Screen.Categories.route) {
                CategoriesScreen(viewModel = viewModel)
            }

            composable(Screen.About.route) {
                AboutBelkuchiScreen(viewModel = viewModel)
            }
        }
    }
}

package com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125.AboutScreen
import com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController=navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            MainScreen(navController)
        }
        composable(route = Screen.About.route){
            AboutScreen(navController)
        }
    }
}


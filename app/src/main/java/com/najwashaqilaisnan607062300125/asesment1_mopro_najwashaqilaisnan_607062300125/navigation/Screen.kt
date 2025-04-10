package com.najwashaqilaisnan607062300125.asesment1_mopro_najwashaqilaisnan_607062300125.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
}

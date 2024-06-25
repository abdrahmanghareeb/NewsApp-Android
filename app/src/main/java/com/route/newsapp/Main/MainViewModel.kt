package com.route.newsapp.Main

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

class MainViewModel : ViewModel() {

    //*** causes crash when clicking on any category

//    @Composable
//    fun intializeDrawerState(): DrawerState {
//        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//        return drawerState
//    }
//
//    @Composable
//    fun intializeNavController(): NavHostController {
//        val navController = rememberNavController()
//        return navController
//    }


}
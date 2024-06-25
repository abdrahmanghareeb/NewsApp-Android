package com.route.newsapp.Main

import  android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.route.newsapp.R
import com.route.newsapp.Main.ui.theme.NewsAppTheme
import com.route.newsapp.Categories.categoryContent
import com.route.newsapp.News.newsContent
import com.route.newsapp.Settings.settingsContent
import com.route.newsapp.widgets.category_SideRoute
import com.route.newsapp.widgets.DrawerBody
import com.route.newsapp.widgets.DrawerHeader
import com.route.newsapp.widgets.categorie_Route
import com.route.newsapp.widgets.newsFragment_Route
import com.route.newsapp.widgets.settings_Route
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            NewsAppTheme {
//                val viewModel : MainViewModel = viewModel()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val navController = rememberNavController()
                ModalNavigationDrawer(
                    scrimColor = Color.White,
                    drawerContent =
                    {
                        Column(
                            Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                        ) {
                            DrawerHeader()
                            DrawerBody(
                                navHostController = navController,
                                drawerState
                            )
                        }
                    },
                    drawerState = drawerState
                )
                {
                    Scaffold(topBar = { News_App_topBar(drawerState) })
                    {
//                        News_Sources_ScrollableTabRow(SourcesList = SourcesList.value)
                        NavHost(
                            navController =navController,
                            startDestination = stringResource(id = R.string.Categories)
                        ) {
                            composable(
                                "$categorie_Route"
                            ) {
                                categoryContent(navController)
                            }
                            composable("$newsFragment_Route/{$category_SideRoute}",
                                arguments = listOf(
                                    navArgument(name = "$category_SideRoute") {
                                        type = NavType.StringType
                                    }
                                )) {
                                var argument = it.arguments?.getString("$category_SideRoute")
//                                newsContent(category = currentCategory)
                                newsContent(category = argument!!)
                            }
                            composable("$settings_Route") {
                                settingsContent()
                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {


}

// this function to carry the value of the Scaffold -> top Bar -> variable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun News_App_topBar(
    drawerState: DrawerState,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scope = rememberCoroutineScope()
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.News_App),
                style = TextStyle(Color.White),
                fontSize = 25.sp
            )
        },
        Modifier.clip(
            RoundedCornerShape(
                topStartPercent = 0,
                topEndPercent = 0,
                bottomStartPercent = 60,
                bottomEndPercent = 60
            )
        ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF39A552),
            navigationIconContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icmenu),
                    contentDescription = "icon menu"
                )
            }
        }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    NewsAppTheme() {

    }
}
package com.route.newsapp.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.newsapp.R
import com.route.newsapp.UI.ui.theme.NewsAppTheme
import com.route.newsapp.model.ApiManager
import com.route.newsapp.model.Response
import com.route.newsapp.model.SourcesItem
import retrofit2.Call
import retrofit2.Callback

class MainActivity2 : ComponentActivity() {

    /*
    *eager : created in the compile time
    * var x = 0
    *
    *lazy : created in the run time
    * var x by lazy {  0  }
    *
     */

    val API_Key = "22414d9f32d44c549beb760cc9d48f12"

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var SourcesList: MutableState<List<SourcesItem>> =
                // to render the view in the run time
                remember {
                    //to survive the Recomposition
                    mutableStateOf(listOf())
                }
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                ApiManager
                    .getNewsService()
                    .getNewsSourses(API_Key)
                    .enqueue(object : Callback<Response> {
                        override fun onResponse(
                            call: Call<Response>,
                            response: retrofit2.Response<Response>
                        ) {
                            val body = response.body()
                            Log.e("TAG", "onResponse: ${body?.status} ")
                            Log.e("TAG", "onResponse: ${body?.sources} ")
                            SourcesList.value = body?.sources as List<SourcesItem> ?: listOf()
                        }

                        override fun onFailure(call: Call<Response>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
                //working on the Background Thread
                /*.execute() //working on the main thread
                ***the problem when using the main thread "ANR" application not response
                Any code that updates the UI or interacts with the user should be run on the main thread.
                Worker threads are used for background tasks that should not block the main thread,
                 such as network requests, database operations, and image processing */

//                ModalNavigationDrawer(drawerContent = {
//                    Column(Modifier.fillMaxSize()) {
//                        DrawerHeader()
//                    }
//                }) {
//                }

                Scaffold(topBar = { News_App_topBar() }) {
                    News_Sources_ScrollableTabRow(SourcesList = SourcesList.value)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun News_App_topBar() {
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
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "icon menu"
                )
            }
        }
    )
}

@Composable
fun News_Sources_ScrollableTabRow(
    SourcesList: List<SourcesItem>
) {
    if (SourcesList.isNotEmpty()) {
        var selectedIndex by remember {
            mutableStateOf(0)
        }
        ScrollableTabRow(
            selectedTabIndex = selectedIndex,
            containerColor = Color.Transparent,
            divider = {},
            indicator = {}
        ) {
            SourcesList.forEachIndexed { index, sourcesItem ->
                Tab(
                    selected = index == selectedIndex,
                    onClick = {
                        selectedIndex = index
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color(0xFF39A552),
                    modifier =
                    if (index == selectedIndex) Modifier
                        .background(color = Color(0xFF39A552), RoundedCornerShape(50))
                    else Modifier
                        .border(2.dp, Color(0xFF39A55), RoundedCornerShape(50))
                        .padding(10.dp)
                )
                {
                    sourcesItem.name?.let { Text(text = it, fontSize = 15.sp) } ?: ""
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    NewsAppTheme() {
        News_Sources_ScrollableTabRow(
            SourcesList = listOf()
        )
    }
}
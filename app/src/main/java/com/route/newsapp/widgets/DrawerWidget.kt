package com.route.newsapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.route.newsapp.R
import kotlinx.coroutines.launch

@Composable
fun DrawerHeader() {
    Text(
        text = stringResource(id = R.string.news_app),
        fontSize = 20.sp,
        style = TextStyle(Color.White),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth(.75f)
            .background(
                Color(0xFF39A552)
            )
            .padding(20.dp)
    )

}

@Composable
fun DrawerBody(navHostController: NavHostController , drawerState: DrawerState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        DrawerItem(
            ic_id = R.drawable.ic_categories,
            text_id = R.string.Categories,
            navHostController = navHostController,
            categorie_Route,
            drawerState
        )
        Spacer(
            modifier = Modifier.size(30.dp)
//            .background(Color.White)
        )
        DrawerItem(
            ic_id = R.drawable.ic_settings,
            text_id = R.string.Settings,
            navHostController = navHostController,
            settings_Route,
            drawerState
        )
    }
}

@Composable
fun DrawerItem(ic_id: Int, text_id: Int, navHostController: NavHostController, Route: String , drawerState: DrawerState) {

    val scope = rememberCoroutineScope()

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
        ) {
        Icon(
            painter = painterResource(id = ic_id),
            contentDescription = stringResource(id = text_id), modifier = Modifier.clickable {
                navHostController.navigate("$Route")
                scope.launch {
                    drawerState.close()
                }
            }
        )
        Spacer(modifier = Modifier.size(10.dp))
//        Text(
//            text = stringResource(id = text_id)
//            , fontSize = 20.sp
//            , Modifier.clickable {
//            navHostController.navigate("$Route")
//        })

        TextButton(onClick = {
            navHostController.navigate("$Route")
            scope.launch {
                drawerState.close()
            }
        }) {
            Text(text = stringResource(id = text_id), fontSize = 20.sp)
        }
    }
}

@Preview
@Composable
fun preview() {
//    DrawerBody()
}


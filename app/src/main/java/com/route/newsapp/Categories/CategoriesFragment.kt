package com.route.newsapp.Categories

import androidx.fragment.app.Fragment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.route.newsapp.Main.ui.theme.NewsAppTheme
import com.route.newsapp.widgets.constants
import com.route.newsapp.widgets.currentCategory
import com.route.newsapp.widgets.newsFragment_Route

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CategoriesFragment : Fragment() {


}


@Composable
fun categoryContent(navHostController: NavHostController) {
    Column(
        modifier = Modifier.padding(top = 75.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Pick your category\n" + "of interest",
            fontSize = 20.sp,
            color = Color(0xff4F5A69),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 15.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ), content = {
                items(6) { index ->
                    cardContent(index = index , navHostController = navHostController)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cardContent(index: Int ,  navHostController: NavHostController) {
    Card(
        colors = CardDefaults.cardColors(constants.listOfCategories.get(index).background),
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .height(150.dp),
        onClick = {
            currentCategory= constants.listOfCategories.get(index).category_id
            navHostController.navigate("$newsFragment_Route/$currentCategory")

        }
        ,
        shape = if (index % 2 == 0) {
            RoundedCornerShape(
                topStart = 14.dp,
                topEnd = 14.dp,
                bottomStart = 14.dp,
                bottomEnd = 0.dp
            )
        } else {
            RoundedCornerShape(
                topStart = 14.dp,
                topEnd = 14.dp,
                bottomStart = 0.dp,
                bottomEnd = 14.dp
            )
        }
    ) {
        Icon(
            painter = painterResource(id = constants.listOfCategories.get(index).icon),
            contentDescription = "cardIc",
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .size(90.dp),
            tint = Color.Unspecified
        )
        Text(
            text = constants.listOfCategories.get(index).name,
            fontSize = 20.sp,
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun preview() {
    NewsAppTheme {
//        categorieContent()
    }
}
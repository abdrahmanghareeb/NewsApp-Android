package com.route.newsapp.News

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.route.newsapp.API_model.ApiManager
import com.route.newsapp.API_model.ArticlesItem
import com.route.newsapp.API_model.NewsResponse
import com.route.newsapp.API_model.Response
import com.route.newsapp.API_model.SourcesItem
import com.route.newsapp.Main.ui.theme.NewsAppTheme
import com.route.newsapp.widgets.API_Key
import retrofit2.Call
import retrofit2.Callback

class NewsFragment : Fragment() {

}


@Composable
fun newsContent(
    category: String,
    viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    viewModel.getNewsSources(viewModel.SourcesList, category = category)
    Column(
        modifier = Modifier.padding(top = 75.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        News_Sources_ScrollableTabRow(viewModel.SourcesList.value, viewModel.newsContentList)
        getNewsContent(viewModel.newsContentList)
    }
}

// function to set the tabs of the Sports card
@Composable
fun News_Sources_ScrollableTabRow(
    SourcesList: List<SourcesItem>,
    newsContentList: MutableState<List<ArticlesItem?>?>,
    viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    if (SourcesList.isNotEmpty()) {

        viewModel.getNewsBySources(sourcesItem = SourcesList.get(viewModel.selectedIndex), newsContentList)
        ScrollableTabRow(
            selectedTabIndex = viewModel.selectedIndex,
            containerColor = Color.Transparent,
            divider = {},
            indicator = {},
//            modifier = Modifier.padding(5.dp)
        ) {
            SourcesList.forEachIndexed { index, sourcesItem ->
                Tab(
                    selected = index == viewModel.selectedIndex,
                    onClick = {
                        viewModel.getNewsBySources(sourcesItem = sourcesItem, newsContentList)
                        viewModel.selectedIndex = index
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color(0xFF39A552),
                    modifier =
                    if (index == viewModel.selectedIndex) Modifier
                        .padding(end = 2.dp)
                        .background(
                            color = Color(0xFF39A552),
                            RoundedCornerShape(50)
                        )
                    else Modifier
                        .border(2.dp, Color(0xFF39A552), RoundedCornerShape(50))
                        .padding(end = 2.dp)
                )
                {
                    Text(
                        text = sourcesItem.name ?: "",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(9.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun getNewsContent(newsContentList: MutableState<List<ArticlesItem?>?>, viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    LazyColumn {
        items(newsContentList.value!!.size) {
            newsCard(articlesItem = newsContentList.value!!.get(it)!!)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun newsCard(articlesItem: ArticlesItem ,viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        GlideImage(
            model = articlesItem.urlToImage,
            contentDescription = "news picture",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = articlesItem.author ?: "", color = Color(0xff79828B))
        Text(text = articlesItem.title ?: "", color = Color(0xff42505C))
        Text(
            text = articlesItem.publishedAt ?: "",
            textAlign = TextAlign.End,
            color = Color(0xffA3A3A3),
            modifier = Modifier.fillMaxWidth()
        )

    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCard() {
    NewsAppTheme {
        newsCard(
            articlesItem =
            ArticlesItem(
                publishedAt = "3 hours ago",
                author = "abdo",
                urlToImage = "https://media.wired.com/photos/6525c8ac419624284be05210/191:100/w_1280,c_limit/HANF-Michael%20Casey.jpg",
                title = "Why are football's biggest clubs starting a new \n" +
                        "tournament?"
            )
        )

    }
}
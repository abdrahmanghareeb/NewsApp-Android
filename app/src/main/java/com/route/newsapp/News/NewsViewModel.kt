package com.route.newsapp.News

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.route.newsapp.API_model.ApiManager
import com.route.newsapp.API_model.ArticlesItem
import com.route.newsapp.API_model.NewsResponse
import com.route.newsapp.API_model.Response
import com.route.newsapp.API_model.SourcesItem
import com.route.newsapp.widgets.API_Key
import retrofit2.Call
import retrofit2.Callback

class NewsViewModel : ViewModel() {
    //create list of Source items
    var SourcesList: MutableState<List<SourcesItem>> =
            mutableStateOf(listOf())

    var newsContentList: MutableState<List<ArticlesItem?>?> =
            mutableStateOf(listOf())
    //to help selecting another tab
    var selectedIndex by mutableStateOf(0)

    fun getNewsSources(SourcesList: MutableState<List<SourcesItem>>, category: String) {
        ApiManager
            .getNewsService()
            .getNewsSources(API_Key, category = category)
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
    }

    fun getNewsBySources(
        sourcesItem: SourcesItem,
        articlesItemsList: MutableState<List<ArticlesItem?>?>
    ) {
        ApiManager
            .getNewsService()
            .getNewsBySource(API_Key, sourcesItem.id!!)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: retrofit2.Response<NewsResponse>
                ) {
                    val newsResponse = response.body()
                    articlesItemsList.value = newsResponse?.articles
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}
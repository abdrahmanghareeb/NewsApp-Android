package com.route.newsapp.widgets

import androidx.compose.ui.graphics.Color
import com.route.newsapp.R

object constants {
    var listOfCategories: List<category> = listOf<category>(
        category(name = "Sports", background = Color(0xffC91C22), icon = R.drawable.sports, category_id = "sports"),
        category(name = "Technology", background = Color(0XfF003E90), icon = R.drawable.politics , category_id = "technology"),
        category(name = "Health", background = Color(0xffED1E79), icon = R.drawable.health, category_id = "health"),
        category(name = "Business", background = Color(0xffCF7E48), icon = R.drawable.bussines, category_id = "business"),
        category(name = "General", background = Color(0xff4882CF), icon = R.drawable.environment, category_id = "general"),
        category(name = "Science", background = Color(0xffF2D352), icon = R.drawable.science, category_id = "science")
    )
}

val API_Key = "22414d9f32d44c549beb760cc9d48f12"
val newsFragment_Route = "newsFragment"
val settings_Route = "settings"
val categorie_Route = "categories"
const val category_SideRoute = "category"
var currentCategory : String = "sports"


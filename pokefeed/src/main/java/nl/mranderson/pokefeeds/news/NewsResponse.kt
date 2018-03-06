package nl.mranderson.pokefeeds.news

import nl.mranderson.pokefeeds.news.model.NewsItem

class NewsResponse {
    var items: List<NewsItem>? = null
    var exception: Exception? = null
}
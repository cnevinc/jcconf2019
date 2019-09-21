package com.cnevinchen.news

class GoogleRssFeedRepository constructor(private val googleRssFeedClient: GoogleRssFeedClient) {

    fun news(language: String): List<FeedItem>? {
        val rss = googleRssFeedClient.rss(language).execute().body()
        return rss?.feedItems
    }
}
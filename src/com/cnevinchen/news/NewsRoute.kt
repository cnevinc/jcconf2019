package com.cnevinchen.news

import com.cnevinchen.di.googleNewsFeedService
import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import java.util.concurrent.TimeUnit

private val cacheGoogleNews = CacheBuilder.newBuilder()
    .maximumSize(100) // Size-based Eviction
    .refreshAfterWrite(15, TimeUnit.MINUTES) // Timed Eviction
    .recordStats()
    .build(
        object : CacheLoader<String, List<FeedItem>>() {
            override fun load(key: String): List<FeedItem> {
                return googleNewsFeedService.getNews(key) ?: listOf()
            }
        })

fun Routing.news() {
    get("/news") {
        val lang = call.request.queryParameters["lang"]
        if (lang == null) {
            call.respond(HttpStatusCode.BadRequest, "Please specify language")
        } else {
            val list = cacheGoogleNews.get(lang) ?: call.respond(HttpStatusCode.NoContent)
            call.respond(list)
        }
    }
}


package com.cnevinchen.di

import com.cnevinchen.news.GoogleNewsFeedService
import com.cnevinchen.news.GoogleRssFeedClientConfiguration
import com.cnevinchen.news.GoogleRssFeedRepository


val client = GoogleRssFeedClientConfiguration().GoogleRssFeedClientFactory()
val googleRssFeedRepository = GoogleRssFeedRepository(client)
val googleNewsFeedService = GoogleNewsFeedService(googleRssFeedRepository)

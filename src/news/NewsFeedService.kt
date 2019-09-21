package com.cnevinchen.news

class GoogleNewsFeedService constructor(private val googleRssFeedRepository: GoogleRssFeedRepository) {

    fun getNews(language: String) = googleRssFeedRepository.news(language)

}


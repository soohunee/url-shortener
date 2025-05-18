package com.cold.urlshortener.controller

data class ShortenUrlRequest(
    val originalUrl: String,
    val issuer: String
)

data class ShortenUrlResponse(
    val shortUrl: String
)
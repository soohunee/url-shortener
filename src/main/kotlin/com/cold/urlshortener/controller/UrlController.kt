package com.cold.urlshortener.controller

import com.cold.urlshortener.service.UrlShortener
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/v1")
class UrlController(
    private val urlShortener: UrlShortener
) {
    @PostMapping("/shorten-url")
    fun shortenUrl(@RequestBody shortenUrlRequest: ShortenUrlRequest): ShortenUrlResponse {
        val shortUrl = urlShortener.shortenUrl(shortenUrlRequest.originalUrl, shortenUrlRequest.issuer)

        return ShortenUrlResponse(shortUrl)
    }

    @GetMapping("redirect")
    fun redirect() {
        //TODO: 301
    }
}
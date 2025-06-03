package com.cold.urlshortener.controller

import com.cold.urlshortener.service.UrlFinder
import com.cold.urlshortener.service.UrlShortener
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI


@RestController
class UrlController(
    private val urlShortener: UrlShortener,
    private val urlFinder: UrlFinder
) {
    @PostMapping("/shorten-url")
    fun shortenUrl(@RequestBody shortenUrlRequest: ShortenUrlRequest): ShortenUrlResponse {
        val shortUrl = urlShortener.shortenUrl(shortenUrlRequest.originalUrl, shortenUrlRequest.issuer)

        return ShortenUrlResponse(shortUrl)
    }

    @GetMapping("/{shortKey}")
    fun redirect(@PathVariable shortKey: String): ResponseEntity<Any> {
        val redirectUrl = urlFinder.findOriginalUrl(shortKey)

        val headers = HttpHeaders().apply {
            location = URI.create(redirectUrl)
        }

        return ResponseEntity<Any>(headers, HttpStatus.MOVED_PERMANENTLY)
    }
}
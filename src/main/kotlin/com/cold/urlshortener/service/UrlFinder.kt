package com.cold.urlshortener.service

import com.cold.urlshortener.repository.UrlRepository
import org.springframework.stereotype.Service

@Service
class UrlFinder(private val urlRepository: UrlRepository) {
    fun findOriginalUrl(shorKey: String): String {
        val originalUrl =
            urlRepository.findByShortKey(shorKey)?.originalUrl ?: throw RuntimeException("original url not found")

        return originalUrl.withScheme()
    }

    private fun String.withScheme() = if (!this.lowercase().startsWith("http")) "https://$this" else this
}
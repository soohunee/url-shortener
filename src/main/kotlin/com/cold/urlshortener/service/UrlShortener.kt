package com.cold.urlshortener.service

import com.cold.urlshortener.entity.UrlEntity
import com.cold.urlshortener.repository.UrlRepository
import com.cold.urlshortener.support.Base62Encoder
import com.cold.urlshortener.support.IdGenerator
import org.springframework.stereotype.Service

@Service
class UrlShortener(private val urlRepository: UrlRepository) {
    fun shortenUrl(originalUrl: String, issuer: String): String {
        val generatedId = IdGenerator.generateId()
        val shortUrl = Base62Encoder.encode(generatedId)

        saveUrl(generatedId, originalUrl, shortUrl, issuer)

        return shortUrl
    }

    private fun saveUrl(generatedId: Long, originalUrl: String, shortUrl: String, issuer: String) {
        val urlEntity = UrlEntity(generatedId, originalUrl, shortUrl, issuer)

        urlRepository.save(urlEntity)
    }
}
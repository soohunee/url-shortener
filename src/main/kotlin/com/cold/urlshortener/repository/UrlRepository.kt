package com.cold.urlshortener.repository

import com.cold.urlshortener.entity.UrlEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UrlRepository : JpaRepository<UrlEntity, Long> {
}
package com.cold.urlshortener.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "url")
class UrlEntity(
    @Id
    @Column(name = "id", nullable = false, unique = true, length = 36)
    val id: Long,

    @Column(name = "original_url", nullable = false, length = 255)
    var originalUrl: String,

    @Column(name = "short_key", nullable = false, length = 255)
    var shortKey: String,

    @Column(name = "issuer", nullable = false, length = 255)
    var issuer: String,
)
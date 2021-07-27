package com.musinsa.shared.domain.valueobject

import java.util.UUID

@Suppress("UnnecessaryAbstractClass")
abstract class UUIDValueObject(value: String) : ValueObject<String>(value) {
    init {
        UUID.fromString(value).toString()
    }

    fun toUUID(): UUID {
        return UUID.fromString(value)
    }
}

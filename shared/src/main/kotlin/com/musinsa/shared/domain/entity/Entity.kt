package com.musinsa.shared.domain.entity

@Suppress("UnnecessaryAbstractClass")
abstract class Entity<T : Any>(private val id: T) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Entity<*>

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

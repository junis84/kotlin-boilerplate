package com.musinsa.shared.domain.valueobject

import java.rmi.UnexpectedException

enum class GlobalFilter(val value: String) {
    ALL("A"),
    FEMALE("F"),
    MALE("M");

    override fun toString(): String {
        return "GlobalFilter(value=$value)"
    }
}

class Sex(value: Int) : NumberValueObject<Int>(value)

enum class SiteKind(val value: Int) {
    ALL(value = 0),
    MUSINSA(value = 2),
    WUSINSA(value = 4),
    PLAYER(value = 8);

    override fun toString(): String {
        return "SiteKind(value=$value)"
    }

    companion object {
        fun of(value: Int): SiteKind {
            return values().firstOrNull { it.value == value }
                ?: throw UnexpectedException("Value '$value' is not part of the enum ${SiteKind::class.java}")
        }
    }
}

enum class UseYn(val value: String) {
    YES("Y"),
    NO("N");

    override fun toString(): String {
        return "UseYn(value=$value)"
    }

    companion object {
        fun of(value: String): UseYn {
            return values().firstOrNull { it.value == value }
                ?: throw UnexpectedException("Value '$value' is not part of the enum ${UseYn::class.java}")
        }
    }
}

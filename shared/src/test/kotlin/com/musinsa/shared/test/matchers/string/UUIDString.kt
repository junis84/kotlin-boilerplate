package com.musinsa.shared.test.matchers.string

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.shouldNotBe
import java.util.UUID

private fun toUUID(value: String): UUID {
    return UUID.fromString(value)
}

fun <T> beValidUUIDString(): Matcher<String?> = object : Matcher<String?> {
    override fun test(value: String?): MatcherResult {
        val passed = if (value == null) {
            false
        } else @Suppress("SwallowedException") try {
            toUUID(value)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
        return MatcherResult(
            passed,
            { "\"$value\" should be valid UUID string" },
            { "\"$value\" should not be valid UUID string" }
        )
    }
}

fun String?.shouldValidUUIDString() {
    this shouldNotBe null
    shouldNotThrow<IllegalArgumentException> {
        toUUID(this as String)
    }
}

package com.musinsa.shared.test.matchers.string

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.shouldNotBe
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

private fun toLocalDateTime(value: String): LocalDateTime {
    return LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME)
}

fun <T> beValidISODateTimeString(): Matcher<String?> = object : Matcher<String?> {
    override fun test(value: String?): MatcherResult {
        val passed = if (value == null) false else try {
            toLocalDateTime(value)
            true
        } catch (e: DateTimeParseException) {
            false
        }
        return MatcherResult(
            passed,
            { "\"$value\" should be valid date-time string" },
            { "\"$value\" should not be valid date-time string" }
        )
    }
}

fun String?.shouldValidISODateTimeString() {
    this shouldNotBe null
    shouldNotThrow<DateTimeParseException> {
        toLocalDateTime(this as String)
    }
}

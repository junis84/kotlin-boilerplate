package com.musinsa.codi.faker

import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone
import kotlin.random.Random

internal fun randomDateTimeUntilNow(): LocalDateTime {
    val endTime = Instant.now()
    val startTime = endTime.minus(Duration.ofDays(365))
    return LocalDateTime.ofInstant(
        Instant.ofEpochSecond(Random.nextLong(startTime.epochSecond, endTime.epochSecond)),
        TimeZone.getDefault().toZoneId()
    )
}
